package com.google.appinventor.web.services;

import com.google.appinventor.web.model.FileData;
import com.google.appinventor.web.model.ProjectData;
import com.google.appinventor.web.model.ProjectFileId;
import com.google.appinventor.web.model.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileExporterImpl implements FileExporter {

    @Autowired
    private ProjectDataRepository projectDataRepository;

    @Autowired
    private FileDataRepository fileDataRepository;

    @Override
    public RawFile exportProjectToZip(String currentUser, Long projectId, boolean includeYail) {

        try {
            ProjectData projectData = projectDataRepository.getOne(projectId);

            ByteArrayOutputStream zipFile = new ByteArrayOutputStream();
            final ZipOutputStream out = new ZipOutputStream(zipFile);
            out.setComment("Built with MIT App Inventor");

            fileDataRepository.findByProject(projectId).stream()
                    .filter(fd -> fd.getRole() == RoleEnum.SOURCE)
                    .filter(fd -> includeYail || !fd.getId().getFileId().endsWith(".yail"))
                    .forEach(fd -> {
                        try {
                            out.putNextEntry(new ZipEntry(fd.getId().getFileId()));
                            out.write(fd.getContent());
                            out.closeEntry();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            out.close();

            String filename = projectData.getName() + ".aia";
            return new RawFile(filename, zipFile.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RawFile exportProjectOutputFile(String currentUser, Long projectId, String target) {
        ProjectFileId fileId = fileDataRepository.findOutputByProject(projectId).stream()
                .filter(fd -> (target == null || fd.getFileId().startsWith("build/" + target + "/"))
                        && fd.getFileId().endsWith(".apk"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("output not found"));
        FileData fileData = fileDataRepository.getOne(fileId);
        return new RawFile(StorageUtils.basename(fileData.getId().getFileId()), fileData.getContent());
    }
}

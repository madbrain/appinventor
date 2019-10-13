package com.google.appinventor.web.controllers.receive;

import com.google.appinventor.web.model.FileData;
import com.google.appinventor.web.model.ProjectFileId;
import com.google.appinventor.web.model.RoleEnum;
import com.google.appinventor.web.model.UserProjectId;
import com.google.appinventor.web.services.BuildStatusService;
import com.google.appinventor.web.services.FileDataRepository;
import com.google.appinventor.web.services.SecurityService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/api/receive-build")
public class ReceiveBuildController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveBuildController.class);

    private static final String ANDROID_KEYSTORE_FILENAME = "android.keystore";

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BuildStatusService buildStatusService;

    @Autowired
    private FileDataRepository fileDataRepository;

    @PostMapping("/{encryptedUserAndProjectId}/**")
    public void receiveBuild(
            @PathVariable("encryptedUserAndProjectId") String encryptedUserAndProjectId,
            HttpServletRequest request) {

        UserProjectId userProjectId = securityService.decryptIds(encryptedUserAndProjectId);
        String buildFileDirPath = new AntPathMatcher()
                .extractPathWithinPattern("/api/receive-build/{project}/**", request.getRequestURI());

        LOGGER.info("project " + userProjectId.getProjectId());
        LOGGER.info("userId " + userProjectId.getUserId());
        LOGGER.info("path " + buildFileDirPath);

        try {
            ZipInputStream zipInputStream = new ZipInputStream(request.getInputStream());
            while (true) {
                ZipEntry zipEntry = zipInputStream.getNextEntry();
                if (zipEntry == null) {
                    break;
                }
                String fileName = zipEntry.getName();
                byte[] fileBytes = IOUtils.toByteArray(zipInputStream);
                if (ANDROID_KEYSTORE_FILENAME.equals(fileName)) {
                    LOGGER.info("Saving android.keystore for user: " + userProjectId.getUserId());
//                    storageIo.addFilesToUser(userId, StorageUtil.ANDROID_KEYSTORE_FILENAME);
//                    storageIo.uploadRawUserFile(userId, fileName, fileBytes);
                } else if (fileName.equals("build.status")) {
                    int progress = Integer.parseInt((new String(fileBytes)).trim());
                    LOGGER.info("Received a build.status file contents = " + progress);
                    buildStatusService.storeBuildStatus(userProjectId.getUserId(), userProjectId.getProjectId(), progress);
                } else {
                    String filePath = buildFileDirPath + "/" + fileName;
                    LOGGER.info("Info " + filePath);
                    FileData fileData = new FileData();
                    fileData.setContent(fileBytes);
                    fileData.setRole(RoleEnum.TARGET);
                    fileData.setUserId(userProjectId.getUserId());
                    fileData.setId(new ProjectFileId(userProjectId.getProjectId(), filePath));
                    fileDataRepository.save(fileData);
                    buildStatusService.storeBuildStatus(userProjectId.getUserId(), userProjectId.getProjectId(), 0); // Reset for the next build
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

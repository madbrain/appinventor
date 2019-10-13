package com.google.appinventor.web.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appinventor.web.controllers.project.*;
import com.google.appinventor.web.controllers.project.nodes.*;
import com.google.appinventor.web.model.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Service
public class YoungAndroidProjectManager implements ProjectManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(YoungAndroidProjectManager.class);

    private static final String PROJECT_DIRECTORY = "youngandroidproject";
    public static final String PROJECT_PROPERTIES_FILE_NAME = PROJECT_DIRECTORY + "/" + "project.properties";

    private static final String YOUNG_ANDROID_PROJECT_TYPE = "YoungAndroid";

    @Value("${buildserver.url}")
    private String buildServerUrl;

    @Value("${public.url}")
    private String publicUrl;

    @Autowired
    private SecurityService securityService;

    private static String FORM_CONTENT(String appName, String screenName) {
        return "#|\n" +
                "$JSON\n" +
                "{\"authURL\":[],\"YaVersion\":\"184\",\"Source\":\"Form\",\"Properties\":{\"$Name\":\"" + screenName
                + "\",\"$Type\":\"Form\",\"$Version\":\"24\",\"Uuid\":\"0\",\"Title\":\"" + screenName
                + "\",\"AppName\":\"" + appName + "\"}}\n" +
                "|#";
    }

    private static final String ASSETS_FOLDER = "assets";
    private static final String SRC_FOLDER = "src";
    private static final String FORM_EXTENSION = ".scm";
    private static final String BLOCKS_EXTENSION = ".bky";

    @Autowired
    private ProjectDataRepository projectDataRepository;

    @Autowired
    private UserProjectDataRepository userProjectDataRepository;

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private FileExporter fileExporter;

    @Autowired
    private BuildStatusService buildStatusService;

    @Override
    public String getProjectType() {
        return YOUNG_ANDROID_PROJECT_TYPE;
    }

    @Override
    @Transactional
    public long newProject(String userId, String projectName, NewProjectParameters params) {
        NewYoungAndroidProjectParameters parameters = (NewYoungAndroidProjectParameters) params;

        String qualifiedName = parameters.getPackageName() + "." + parameters.getFormName();

        ProjectSettings settings = ProjectSettings.makeSettings(projectName);
        String jesonSettings = makeJson(settings);

        long projectId = createProject(projectName, jesonSettings, userId);

        FileData formFile = createFileData(projectId,
                PROJECT_PROPERTIES_FILE_NAME, userId, toProperties(qualifiedName, projectName, settings));
        fileDataRepository.save(formFile);

        createScreenFiles(projectId, qualifiedName, userId);

        return projectId;
    }

    private void writeIfNotNull(PrintWriter writer, String name, String value) {
        if (value != null && !value.isEmpty()) {
            writer.println(name + "=" + value);
        }
    }

    private byte[] toProperties(String qualifiedName, String projectName, ProjectSettings settings) {
        StringWriter out = new StringWriter();
        PrintWriter writer = new PrintWriter(out);
        writer.println("main=" + qualifiedName);
        writer.println("name=" + projectName);
        writer.println("assets=../" + ASSETS_FOLDER);
        writer.println("source=../" + SRC_FOLDER);
        writer.println("build=../build");
        writeIfNotNull(writer, "icon", settings.SimpleSettings.Icon);
        writeIfNotNull(writer, "versioncode", settings.SimpleSettings.VersionCode);
        writeIfNotNull(writer, "versionname", settings.SimpleSettings.VersionName);
        writeIfNotNull(writer, "useslocation", settings.SimpleSettings.UsesLocation);
        writeIfNotNull(writer, "aname", settings.SimpleSettings.AppName);
        writeIfNotNull(writer, "sizing", settings.SimpleSettings.Sizing);
        writeIfNotNull(writer, "showlistsasjson", settings.SimpleSettings.ShowListsAsJson);
        writeIfNotNull(writer, "tutorialurl", settings.SimpleSettings.TutorialURL);
        writeIfNotNull(writer, "actionbar", settings.SimpleSettings.ActionBar);
        writeIfNotNull(writer, "theme", settings.SimpleSettings.Theme);
        writeIfNotNull(writer, "color.primary", settings.SimpleSettings.PrimaryColor);
        writeIfNotNull(writer, "color.primary.dark", settings.SimpleSettings.PrimaryColorDark);
        writeIfNotNull(writer, "color.accent", settings.SimpleSettings.AccentColor);
        return out.toString().getBytes();
    }

    private long createProject(String projectName, String settings, String userId) {
        Date now = new Date();
        ProjectData projectData = new ProjectData();
        projectData.setName(projectName);
        projectData.setType(YOUNG_ANDROID_PROJECT_TYPE);
        // TODO settings must also be saved in properties file
        projectData.setSettings(settings);
        projectData.setDateCreated(now);
        projectData.setDateModified(now);
        projectData.setAttributionId(0L);
        projectData.setGalleryId(0L);

        projectDataRepository.save(projectData);

        UserProjectData userProjectData = new UserProjectData();
        userProjectData.setId(new UserProjectId(projectData.getId(), userId));
        userProjectData.setState(StateEnum.OPEN);
        userProjectData.setSettings(settings);
        userProjectDataRepository.save(userProjectData);

        return projectData.getId();
    }

    private void createScreenFiles(Long projectId, String qualifiedName, String userId) {
        String[] elements = qualifiedName.split("\\.");
        String appName = elements[2];
        String screenName = elements[3];
        FileData formFile = createFileData(projectId,
                getFilename(qualifiedName, FORM_EXTENSION), userId, FORM_CONTENT(appName, screenName).getBytes());
        fileDataRepository.save(formFile);

        FileData blocksFile = createFileData(projectId,
                getFilename(qualifiedName, BLOCKS_EXTENSION), userId, "".getBytes());
        fileDataRepository.save(blocksFile);
    }

    @Override
    public ProjectRootNode getProjectRootNode(String userId, Long projectId) {
        ProjectData projectData = projectDataRepository.getOne(projectId);

        YoungAndroidProjectNode root = new YoungAndroidProjectNode();
        root.setFileId(String.valueOf(projectId));
        root.setProjectId(projectId);
        root.setName(projectData.getName());
        root.setType(YOUNG_ANDROID_PROJECT_TYPE);

        YoungAndroidAssetsFolder assets = new YoungAndroidAssetsFolder();
        assets.setParent(root);
        assets.setFileId(ASSETS_FOLDER);
        assets.setName("Assets");
        root.getChildren().add(assets);

        YoungAndroidComponentsFolder components = new YoungAndroidComponentsFolder();
        components.setParent(root);
        components.setFileId(ASSETS_FOLDER + "/external_comps");
        components.setName("Components");
        root.getChildren().add(components);

        YoungAndroidSourceFolderNode src = new YoungAndroidSourceFolderNode();
        src.setParent(root);
        src.setFileId(SRC_FOLDER);
        src.setName("Sources");
        root.getChildren().add(src);

        Map<String, YoungAndroidPackageNode> packageMap = new HashMap<>();

        fileDataRepository.findByProject(projectId).forEach(file -> {
            String fileId = file.getId().getFileId();

            if (fileId.startsWith(ASSETS_FOLDER + "/")) {
                // XXX add to assets or components
            } else if (fileId.startsWith(SRC_FOLDER + "/")) {
                YoungAndroidSourceNode source = null;
                if (fileId.endsWith(FORM_EXTENSION)) {
                    source = new YoungAndroidFormNode();
                    source.setFileId(fileId);
                    source.setName(StorageUtils.basename(fileId));
                } else if (fileId.endsWith(BLOCKS_EXTENSION)) {
                    source = new YoungAndroidBlocksNode();
                    source.setFileId(fileId);
                    source.setName(StorageUtils.basename(fileId));
                }

                if (source != null) {
                    String packageName = getPackageName(getQualifiedName(fileId));
                    YoungAndroidPackageNode pack = packageMap.get(packageName);
                    if (pack == null) {
                        pack = new YoungAndroidPackageNode();
                        pack.setParent(src);
                        pack.setFileId(SRC_FOLDER + "/" + packageName.replaceAll("\\.", "/"));
                        pack.setName(packageName);
                        src.getChildren().add(pack);
                        packageMap.put(packageName, pack);
                    }
                    source.setParent(pack);
                    pack.getChildren().add(source);
                }
            }
        });
        return root;
    }

    @Override
    public ChecksumedLoadFile load2(String userId, long projectId, String fileId) {
        byte[] content = fileDataRepository.getOne(new ProjectFileId(projectId, fileId)).getContent();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] retval = md.digest(content);
            String hexval = byteArrayToHexString(retval);
            return new ChecksumedLoadFile(new String(content), hexval);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void storeProjectSettings(String userId, long projectId, String jsonSettings) {
        try {
            ProjectData projectData = projectDataRepository.getOne(projectId);
            projectData.setSettings(jsonSettings);
            ProjectSettings settings = objectMapper.readValue(jsonSettings, ProjectSettings.class);
            FileData propertiesFile = fileDataRepository.getOne(new ProjectFileId(projectId, PROJECT_PROPERTIES_FILE_NAME));
            Properties properties = new Properties();
            properties.load(new ByteArrayInputStream(propertiesFile.getContent()));
            propertiesFile.setContent(toProperties(properties.getProperty("main"), projectData.getName(), settings));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public long save2(String userId, long projectId, String fileId, String content) {
        Optional<FileData> fileData = fileDataRepository.findById(new ProjectFileId(projectId, fileId));
        if (fileData.isPresent()) {
            fileData.get().setContent(content.getBytes());
        } else {
            FileData formFile = createFileData(projectId, fileId, userId, content.getBytes());
            fileDataRepository.save(formFile);
        }
        return modifyProject(projectId);
    }

    @Override
    @Transactional
    public long addFile(String userId, long projectId, String fileId) {
        if (fileId.endsWith(FORM_EXTENSION) || fileId.endsWith(BLOCKS_EXTENSION)) {
            String qualifiedName = getQualifiedName(fileId);

            if (fileDataRepository.countFiles(projectId,
                    Arrays.asList(getFilename(qualifiedName, FORM_EXTENSION), getFilename(qualifiedName, BLOCKS_EXTENSION))) == 0) {
                createScreenFiles(projectId, qualifiedName, userId);

                return modifyProject(projectId);
            } else {
                throw new RuntimeException("files already exists");
            }
        } else {
            throw new RuntimeException("TODO: add normal file");
        }
    }

    @Override
    @Transactional
    public long deleteFile(String userId, long projectId, String fileId) {
        fileDataRepository.deleteById(new ProjectFileId(projectId, fileId));
        return modifyProject(projectId);
    }

    @Override
    @Transactional
    public void deleteProject(String userId, long projectId) {
        userProjectDataRepository.deleteById(new UserProjectId(projectId, userId));
        fileDataRepository.deleteAllOfProject(projectId);
        projectDataRepository.deleteById(projectId);
    }

    @Override
    @Transactional
    public long copyProject(String userId, long projectId, String newName) {
        ProjectData projectData = projectDataRepository.getOne(projectId);
        long newProjectId = createProject(newName, projectData.getSettings(), userId);

        fileDataRepository.findByProject(projectId).forEach(fd -> {
            copyFile(fd, newProjectId, newName, userId);
        });

        return newProjectId;
    }

    @Override
    public RpcResult build(String userId, long projectId, String target) {

        ProjectData projectData = projectDataRepository.getOne(projectId);

        fileDataRepository.findByProject(projectId).stream()
                .filter(file -> file.getRole() == RoleEnum.TARGET)
                .forEach(file -> {
                    fileDataRepository.delete(file);
                });

        try {
            RawFile rawFile = fileExporter.exportProjectToZip(userId, projectId, true);

            // TODO username
            String userName = "username";
            String outputFileDir = "build/" + target;
            HttpURLConnection connection = (HttpURLConnection) new URL(
                    getBuildServerUrlStr(userName, userId, projectId, outputFileDir)).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(connection.getOutputStream());
            bufferedOutputStream.write(rawFile.getContent());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            int responseCode = connection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                String error = "Build server responded with response code " + responseCode + ".";
                error += "\n" + IOUtils.toString(connection.getInputStream(), "UTF-8");
                return new RpcResult(responseCode, "", error);
            }

            return new RpcResult(true, "Building " + projectData.getName(), "");
        } catch (Exception e) {
            return new RpcResult(false, "", e.getMessage());
        }
    }

    @Override
    public RpcResult getBuildResult(String userId, long projectId, String target) {
        String buildOutputFileName = "build/" + target + '/' + "build.out";
        List<ProjectFileId> outputFiles = fileDataRepository.findOutputByProject(projectId);
        int currentProgress = buildStatusService.getBuildStatus(userId, projectId);
        RpcResult buildResult = new RpcResult(-1, String.valueOf(currentProgress), ""); // Build not finished
        for (ProjectFileId outputFile : outputFiles) {
            if (buildOutputFileName.equals(outputFile.getFileId())) {
                byte[] output = fileDataRepository.getOne(outputFile).getContent();
                String outputStr = new String(output, StandardCharsets.UTF_8);
                try {
                    buildResult = objectMapper.readValue(output, RpcResult.class);
                } catch (IOException e) {
                    buildResult = new RpcResult(1, "", "");
                }
                break;
            }
        }
        return buildResult;
    }

    private void copyFile(FileData fd, long newProjectId, String newProjectName, String userId) {
        String[] fileIdComponents = fd.getId().getFileId().split("/");
        String newFileId = String.join("/",
                // src               appinventor          email                project name    file name
                fileIdComponents[0], fileIdComponents[1], fileIdComponents[2], newProjectName, fileIdComponents[4]);
        FileData newFd = new FileData();
        newFd.setId(new ProjectFileId(newProjectId, newFileId));
        newFd.setUserId(userId);
        newFd.setRole(fd.getRole());
        newFd.setSettings(fd.getSettings());
        newFd.setContent(fd.getContent());
        fileDataRepository.save(newFd);
    }

    private long modifyProject(long projectId) {
        Date now = new Date();
        ProjectData projectData = projectDataRepository.getOne(projectId);
        projectData.setDateModified(now);
        return now.getTime();
    }

    private static String getFilename(String qualifiedName, String extension) {
        return SRC_FOLDER + "/" + qualifiedName.replaceAll("\\.", "/") + extension;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            result.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

    public static String getPackageName(String qualifiedName) {
        int index = qualifiedName.lastIndexOf('.');
        return index < 0 ? "" : qualifiedName.substring(0, index);
    }

    public static String getQualifiedName(String sourceFileId) {
        String name = sourceFileId.substring((SRC_FOLDER + "/").length());
        name = trimOffExtension(name);
        return name.replace('/', '.');
    }

    public static String trimOffExtension(String path) {
        int lastSlash = path.lastIndexOf('/');
        int lastDot = path.lastIndexOf('.');
        return (lastDot > lastSlash) ? path.substring(0, lastDot) : path;
    }

    private String makeJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    private FileData createFileData(Long projectId, String formName, String userId, byte[] content) {
        FileData fileData = new FileData();
        fileData.setId(new ProjectFileId(projectId, formName));
        fileData.setRole(RoleEnum.SOURCE);
        fileData.setUserId(userId);
        fileData.setContent(content);
        return fileData;
    }

    private String getBuildServerUrlStr(String userName, String userId, long projectId, String fileName)
            throws UnsupportedEncodingException {
        return buildServerUrl +"/buildserver/build-all-from-zip-async"
                + "?uname=" + URLEncoder.encode(userName, "UTF-8")
                + "&callback="
                + URLEncoder.encode(publicUrl + "/api/receive-build/"
                        + securityService.encryptUserAndProjectId(userId, projectId)
                        + "/" + fileName,
                "UTF-8");
    }
}

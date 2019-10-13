package com.google.appinventor.web.controllers.project;

import com.google.appinventor.web.controllers.project.nodes.ProjectRootNode;
import com.google.appinventor.web.model.ProjectData;
import com.google.appinventor.web.services.ProjectDataRepository;
import com.google.appinventor.web.services.UserProjectDataRepository;
import com.google.appinventor.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private UserProjectDataRepository userProjectDataRepository;

    @Autowired
    private ProjectDataRepository projectDataRepository;

    @Autowired
    private List<ProjectManager> managers;

    @Autowired
    private UserService userService;

    private Map<String, ProjectManager> managerMap;

    @PostConstruct
    public void buildManagerMap() {
        managerMap = managers.stream().collect(Collectors.toMap(ProjectManager::getProjectType, Function.identity()));
    }

    @RequestMapping(value = "/getProjectInfos", method = RequestMethod.GET)
    public List<UserProject> getProjectInfos() {
        String userId = userService.getCurrentUser();
        List<Long> projectIds = userProjectDataRepository.findProjectIdByUser(userId);
        return projectDataRepository.findAllById(projectIds).stream()
                .map(this::toUserProject)
                .collect(Collectors.toList());
    }

    private UserProject toUserProject(ProjectData pd) {
        UserProject userProject = new UserProject();
        userProject.setProjectId(pd.getId());
        userProject.setProjectName(pd.getName());
        userProject.setProjectType(pd.getType());
        userProject.setDateCreated(pd.getDateCreated().getTime());
        userProject.setDateModified(pd.getDateModified().getTime());
        userProject.setGalleryId(Optional.ofNullable(pd.getGalleryId()).orElse(0L));
        userProject.setAttributionId(Optional.ofNullable(pd.getAttributionId()).orElse(0L));
        return userProject;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/retrieveTemplateData")
    public String retrieveTemplateData(String templatePath) {
        return "\"[]\"";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/newProject")
    public UserProject newProject(@RequestBody NewProjectRequest request) {
        String userId = userService.getCurrentUser();
        long projectId = getProjectManager(request.getProjectType())
                .newProject(userId, request.getProjectName(), request.getParams());
        return toUserProject(projectDataRepository.getOne(projectId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getProject/{projectId}")
    public ProjectRootNode getProject(@PathVariable("projectId") Long projectId) {
        String userId = userService.getCurrentUser();
        return getProjectManager(projectId).getProjectRootNode(userId, projectId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/loadProjectSettings/{projectId}")
    public String loadProjectSettings(@PathVariable("projectId") Long projectId) {
        // TODO load settings
        return "\"{}\"";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/load2")
    public ChecksumedLoadFile load2(@RequestBody LoadRequest request) {
        String userId = userService.getCurrentUser();
        return getProjectManager(projectDataRepository.getOne(request.getProjectId()).getType())
                .load2(userId, request.getProjectId(), request.getFileId());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/storeProjectSettings")
    public void storeProjectSettings(@RequestBody StoreProjectSettingsRequest request) {
        String userId = userService.getCurrentUser();
        getProjectManager(request.getProjectId())
                .storeProjectSettings(userId, request.getProjectId(), request.getSettings());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save2")
    public long save2(@RequestBody Save2Request request) {
        String userId = userService.getCurrentUser();
        return getProjectManager(request.getProjectId())
                .save2(userId, request.getProjectId(), request.getFileId(), request.getSource());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveMultiple")
    public long save(@RequestBody SaveMultipleRequest request) {
        String userId = userService.getCurrentUser();
        long result = 0L;
        for (FileDescriptorWithContent f : request.getFilesAndContent()) {
            result = getProjectManager(f.getProjectId())
                    .save2(userId, f.getProjectId(), f.getFileId(), f.getContent());
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addFile")
    public long addFile(@RequestBody AddFileRequest request) {
        String userId = userService.getCurrentUser();
        return getProjectManager(request.getProjectId())
                .addFile(userId, request.getProjectId(), request.getFileId());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/deleteFile")
    public long deleteFile(@RequestBody DeleteFileRequest request) {
        String userId = userService.getCurrentUser();
        return getProjectManager(request.getProjectId())
                .deleteFile(userId, request.getProjectId(), request.getFileId());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteProject/{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        String userId = userService.getCurrentUser();
        getProjectManager(projectId)
                .deleteProject(userId, projectId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/copyProject")
    public UserProject copyProject(@RequestBody CopyProjectRequest request) {
        String userId = userService.getCurrentUser();
        long newProjectId = getProjectManager(request.getProjectId())
                .copyProject(userId, request.getProjectId(), request.getNewName());
        return toUserProject(projectDataRepository.getOne(newProjectId));
    }

    @PostMapping("/build")
    public RpcResult build(@RequestBody BuildRequest request) {
        String userId = userService.getCurrentUser();
        return getProjectManager(request.getProjectId())
                .build(userId, request.getProjectId(), request.getTarget());
    }

    @PostMapping("/getBuildResult")
    public RpcResult getBuildResult(@RequestBody GetBuildResultRequest request) {
        String userId = userService.getCurrentUser();
        return getProjectManager(request.getProjectId())
                .getBuildResult(userId, request.getProjectId(), request.getTarget());
    }

    private ProjectManager getProjectManager(Long projectId) {
        return getProjectManager(projectDataRepository.getOne(projectId).getType());
    }

    private ProjectManager getProjectManager(String projectType) {
        return managerMap.computeIfAbsent(projectType, (key) -> {
            throw new IllegalArgumentException("Unknown project type " + projectType);
        });
    }

}

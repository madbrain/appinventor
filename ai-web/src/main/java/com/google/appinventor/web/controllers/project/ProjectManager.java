package com.google.appinventor.web.controllers.project;

import com.google.appinventor.web.controllers.project.nodes.ProjectRootNode;

public interface ProjectManager {
    String getProjectType();

    long newProject(String userId, String projectName, NewProjectParameters params);

    ProjectRootNode getProjectRootNode(String userId, Long projectId);

    ChecksumedLoadFile load2(String userId, long projectId, String fileId);

    void storeProjectSettings(String userId, long projectId, String settings);

    long save2(String userId, long projectId, String fileId, String content);

    long addFile(String userId, long projectId, String fileId);

    long deleteFile(String userId, long projectId, String fileId);

    void deleteProject(String userId, long projectId);

    long copyProject(String userId, long projectId, String newName);

    RpcResult build(String userId, long projectId, String target);

    RpcResult getBuildResult(String userId, long projectId, String target);
}

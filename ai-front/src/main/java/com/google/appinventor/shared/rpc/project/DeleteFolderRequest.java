package com.google.appinventor.shared.rpc.project;

public class DeleteFolderRequest {
    String sessionId;
    long projectId;
    String directory;

    public DeleteFolderRequest(String sessionId, long projectId, String directory) {
        this.sessionId = sessionId;
        this.projectId = projectId;
        this.directory = directory;
    }

    public DeleteFolderRequest() {}
}

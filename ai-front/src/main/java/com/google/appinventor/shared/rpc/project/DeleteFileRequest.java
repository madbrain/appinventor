package com.google.appinventor.shared.rpc.project;

public class DeleteFileRequest {
    String sessionId;
    long projectId;
    String fileId;

    public DeleteFileRequest(String sessionId, long projectId, String fileId) {
        this.sessionId = sessionId;
        this.projectId = projectId;
        this.fileId = fileId;
    }

    public DeleteFileRequest() {}
}

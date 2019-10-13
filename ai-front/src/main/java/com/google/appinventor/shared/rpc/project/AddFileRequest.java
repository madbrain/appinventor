package com.google.appinventor.shared.rpc.project;

public class AddFileRequest {
    long projectId;
    String fileId;

    public AddFileRequest(long projectId, String fileId) {
        this.projectId = projectId;
        this.fileId = fileId;
    }

    public AddFileRequest() {}
}

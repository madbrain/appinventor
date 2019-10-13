package com.google.appinventor.shared.rpc.project;

public class LoadRequest {
    long projectId;
    String fileId;

    public LoadRequest(long projectId, String fileId) {
        this.projectId = projectId;
        this.fileId = fileId;
    }

    public LoadRequest() {}
}

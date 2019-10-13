package com.google.appinventor.shared.rpc.project;

public class SaveRequest {
    String sessionId;
    long projectId;
    String fileId;
    String source;

    public SaveRequest(String sessionId, long projectId, String fileId, String source) {
        this.sessionId = sessionId;
        this.projectId = projectId;
        this.fileId = fileId;
        this.source = source;
    }

    public SaveRequest() {}
}

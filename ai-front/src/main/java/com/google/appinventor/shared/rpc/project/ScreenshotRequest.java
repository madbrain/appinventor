package com.google.appinventor.shared.rpc.project;

public class ScreenshotRequest {
    String sessionId;
    long projectId;
    String fileId;
    String content;

    public ScreenshotRequest(String sessionId, long projectId, String fileId, String content) {
        this.sessionId = sessionId;
        this.projectId = projectId;
        this.fileId = fileId;
        this.content = content;
    }

    public ScreenshotRequest() {}
}

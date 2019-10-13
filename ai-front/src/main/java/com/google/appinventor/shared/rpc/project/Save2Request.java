package com.google.appinventor.shared.rpc.project;

public class Save2Request {
    String sessionId;
    long projectId;
    String fileId;
    boolean force;
    String source;

    public Save2Request(String sessionId, long projectId, String fileId, boolean force, String source) {
        this.sessionId = sessionId;
        this.projectId = projectId;
        this.fileId = fileId;
        this.force = force;
        this.source = source;
    }

    public Save2Request() {}
}

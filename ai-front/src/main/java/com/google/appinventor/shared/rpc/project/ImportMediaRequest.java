package com.google.appinventor.shared.rpc.project;

public class ImportMediaRequest {
    String sessionId;
    long projectId;
    String url;
    boolean save;

    public ImportMediaRequest(String sessionId, long projectId, String url, boolean save) {
        this.sessionId = sessionId;
        this.projectId = projectId;
        this.url = url;
        this.save = save;
    }

    public ImportMediaRequest() {}
}

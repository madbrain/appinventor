package com.google.appinventor.shared.rpc.project;

public class StoreProjectSettingsRequest {
    String sessionId;
    long projectId;
    String settings;

    public StoreProjectSettingsRequest(String sessionId, long projectId, String settings) {
        this.sessionId = sessionId;
        this.projectId = projectId;
        this.settings = settings;
    }

    public StoreProjectSettingsRequest() {}
}

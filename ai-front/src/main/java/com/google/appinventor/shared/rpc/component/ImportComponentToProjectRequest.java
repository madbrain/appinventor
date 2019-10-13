package com.google.appinventor.shared.rpc.component;

public class ImportComponentToProjectRequest {
    String forOrUrl;
    long projectId;
    String folderPath;

    public ImportComponentToProjectRequest(String forOrUrl, long projectId, String folderPath) {
        this.forOrUrl = forOrUrl;
        this.projectId = projectId;
        this.folderPath = folderPath;
    }
    public ImportComponentToProjectRequest() {}
}

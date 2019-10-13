package com.google.appinventor.shared.rpc.project;

public class RecordCorruptionRequest {
    long projectId;
    String fileId;
    String message;

    public RecordCorruptionRequest(long projectId, String fileId, String message) {
        this.projectId = projectId;
        this.fileId = fileId;
        this.message = message;
    }

    public RecordCorruptionRequest() {}
}

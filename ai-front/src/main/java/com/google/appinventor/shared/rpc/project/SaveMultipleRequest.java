package com.google.appinventor.shared.rpc.project;

import java.util.List;

public class SaveMultipleRequest {
    String sessionId;
    List<FileDescriptorWithContent> filesAndContent;

    public SaveMultipleRequest(String sessionId, List<FileDescriptorWithContent> filesAndContent) {
        this.sessionId = sessionId;
        this.filesAndContent = filesAndContent;
    }

    public SaveMultipleRequest() {}
}

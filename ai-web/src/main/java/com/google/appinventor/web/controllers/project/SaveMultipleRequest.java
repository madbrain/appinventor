package com.google.appinventor.web.controllers.project;

import java.util.List;

public class SaveMultipleRequest {

    private String sessionId;
    private List<FileDescriptorWithContent> filesAndContent;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<FileDescriptorWithContent> getFilesAndContent() {
        return filesAndContent;
    }

    public void setFilesAndContent(List<FileDescriptorWithContent> filesAndContent) {
        this.filesAndContent = filesAndContent;
    }
}

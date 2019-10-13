package com.google.appinventor.web.controllers.project;

public class ChecksumedLoadFile {
    private String content;
    private String checksum;

    public ChecksumedLoadFile(String content, String checksum) {
        this.content = content;
        this.checksum = checksum;
    }

    public ChecksumedLoadFile() {}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}

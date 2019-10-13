package com.google.appinventor.web.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProjectFileId implements Serializable {

    private Long projectId;

    private String fileId;

    public ProjectFileId(Long projectId, String fileId) {
        this.projectId = projectId;
        this.fileId = fileId;
    }

    public ProjectFileId() {}

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}

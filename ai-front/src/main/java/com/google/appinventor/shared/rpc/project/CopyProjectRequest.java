package com.google.appinventor.shared.rpc.project;

public class CopyProjectRequest {
    private long projectId;
    private String newName;

    public CopyProjectRequest(long projectId, String newName) {
        this.projectId = projectId;
        this.newName = newName;
    }

    public CopyProjectRequest() {}

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}

package com.google.appinventor.web.controllers.project.nodes;

public abstract class ProjectRootNode extends ProjectNode {

    private long projectId;

    private String type;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

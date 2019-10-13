package com.google.appinventor.web.controllers.project;

public class GetBuildResultRequest {
    private long projectId;
    private String target;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}

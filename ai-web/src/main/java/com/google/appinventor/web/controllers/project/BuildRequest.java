package com.google.appinventor.web.controllers.project;

public class BuildRequest {
    private long projectId;
    private String nonce;
    private String target;
    private boolean secondBuildserver;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isSecondBuildserver() {
        return secondBuildserver;
    }

    public void setSecondBuildserver(boolean secondBuildserver) {
        this.secondBuildserver = secondBuildserver;
    }
}

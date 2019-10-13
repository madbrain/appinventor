package com.google.appinventor.shared.rpc.project;

public class BuildRequest {
    long projectId;
    String nonce;
    String target;
    boolean secondBuildserver;

    public BuildRequest(long projectId, String nonce, String target, boolean secondBuildserver) {
        this.projectId = projectId;
        this.nonce = nonce;
        this.target = target;
        this.secondBuildserver = secondBuildserver;
    }

    public BuildRequest() {}
}

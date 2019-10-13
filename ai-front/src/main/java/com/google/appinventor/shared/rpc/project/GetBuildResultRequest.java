package com.google.appinventor.shared.rpc.project;

public class GetBuildResultRequest {
    long projectId;
    String target;

    public GetBuildResultRequest(long projectId, String target) {
        this.projectId = projectId;
        this.target = target;
    }

    public GetBuildResultRequest() {}
}

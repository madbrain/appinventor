package com.google.appinventor.shared.rpc.project;

public class DeveloperAppsRequest extends PagedRequest {
    String userId;

    public DeveloperAppsRequest(String userId, int start, int count) {
        super(start, count);
        this.userId = userId;
    }

    public DeveloperAppsRequest() {
        super();
    }

}

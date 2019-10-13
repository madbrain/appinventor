package com.google.appinventor.shared.rpc.project;

public class CheckIfSendAppStatsRequest {
    String userId;
    String adminEmail;
    String currentHost;

    public CheckIfSendAppStatsRequest(String userId, String adminEmail, String currentHost) {
        this.userId = userId;
        this.adminEmail = adminEmail;
        this.currentHost = currentHost;
    }

    public CheckIfSendAppStatsRequest() {

    }
}

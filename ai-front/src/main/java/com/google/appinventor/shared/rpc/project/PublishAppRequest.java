package com.google.appinventor.shared.rpc.project;

public class PublishAppRequest {
    long projectId;
    String title;
    String projectName;
    String description;
    String moreInfo;
    String credit;

    public PublishAppRequest(long projectId, String title, String projectName, String description, String moreInfo, String credit) {
        this.projectId = projectId;
        this.title = title;
        this.projectName = projectName;
        this.description = description;
        this.moreInfo = moreInfo;
        this.credit = credit;
    }

    public PublishAppRequest() {

    }
}

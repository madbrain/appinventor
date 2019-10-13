package com.google.appinventor.shared.rpc.project;

public class NewProjectFromGalleryRequest {
    String appName;
    String aiaPath;
    long attributionId;

    public NewProjectFromGalleryRequest(String appName, String aiaPath, long attributionId) {
        this.appName = appName;
        this.aiaPath = aiaPath;
        this.attributionId = attributionId;
    }

    public NewProjectFromGalleryRequest() {}
}

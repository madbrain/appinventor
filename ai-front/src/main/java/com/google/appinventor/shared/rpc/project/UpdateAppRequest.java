package com.google.appinventor.shared.rpc.project;

public class UpdateAppRequest {
    GalleryApp app;
    boolean newImage;

    public UpdateAppRequest(GalleryApp app, boolean newImage) {
        this.app = app;
        this.newImage = newImage;
    }

    public UpdateAppRequest() {

    }
}

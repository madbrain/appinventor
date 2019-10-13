package com.google.appinventor.shared.rpc.project;

public class AddAppReportRequest {
    GalleryApp app;
    String reportText;

    public AddAppReportRequest(GalleryApp app, String reportText) {
        this.app = app;
        this.reportText = reportText;
    }

    public AddAppReportRequest() {}
}

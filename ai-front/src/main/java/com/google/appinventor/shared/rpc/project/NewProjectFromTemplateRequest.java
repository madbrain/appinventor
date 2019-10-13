package com.google.appinventor.shared.rpc.project;

public class NewProjectFromTemplateRequest {
    String projectName;
    String pathToZip;

    public NewProjectFromTemplateRequest(String projectName, String pathToZip) {
        this.projectName = projectName;
        this.pathToZip = pathToZip;
    }

    public NewProjectFromTemplateRequest() {}
}

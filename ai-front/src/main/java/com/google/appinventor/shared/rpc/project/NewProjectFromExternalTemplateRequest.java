package com.google.appinventor.shared.rpc.project;

public class NewProjectFromExternalTemplateRequest {
    String projectName;
    String zipData;

    public NewProjectFromExternalTemplateRequest(String projectName, String zipData) {
        this.projectName = projectName;
        this.zipData = zipData;
    }

    public NewProjectFromExternalTemplateRequest() {}
}

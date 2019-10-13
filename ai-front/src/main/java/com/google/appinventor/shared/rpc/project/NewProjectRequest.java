package com.google.appinventor.shared.rpc.project;

public class NewProjectRequest {

    String projectType;
    String projectName;
    NewProjectParameters params;

    public NewProjectRequest(String projectType, String projectName, NewProjectParameters params) {
        this.projectType = projectType;
        this.projectName = projectName;
        this.params = params;
    }

    public NewProjectRequest() {}
}

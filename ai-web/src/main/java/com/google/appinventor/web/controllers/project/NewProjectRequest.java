package com.google.appinventor.web.controllers.project;

public class NewProjectRequest {

    private String projectName;
    private String projectType;
    private NewProjectParameters params;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public NewProjectParameters getParams() {
        return params;
    }

    public void setParams(NewProjectParameters params) {
        this.params = params;
    }
}

package com.google.appinventor.web.controllers.project;

public class NewYoungAndroidProjectParameters implements NewProjectParameters {

    private String packageName;
    private String formName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}

package com.google.appinventor.web.services;

public class ProjectSettings {

    public static class InnerProjectSettings {
        public String Icon = "";
        public String VersionCode = "1";
        public String VersionName = "1.0";
        public String UsesLocation = "false";
        public String AppName;
        public String Sizing = "Fixed";
        public String ShowListsAsJson = "false";
        public String TutorialURL = "";
        public String ActionBar = "false";
        public String Theme = "AppTheme.Light.DarkActionBar";
        public String PrimaryColor = "0";
        public String PrimaryColorDark = "0";
        public String AccentColor = "0";
    }

    public InnerProjectSettings SimpleSettings = new InnerProjectSettings();

    public static ProjectSettings makeSettings(String projectName) {
        ProjectSettings settings = new ProjectSettings();
        settings.SimpleSettings.AppName = projectName;
        return settings;
    }
}

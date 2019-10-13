package com.google.appinventor.web.controllers.project.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = YoungAndroidProjectNode.class,
                name = "com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidProjectNode"),
        @JsonSubTypes.Type(value = FolderNode.class,
                name = "com.google.appinventor.shared.rpc.project.FolderNode"),
        @JsonSubTypes.Type(value = PackageNode.class,
                name = "com.google.appinventor.shared.rpc.project.PackageNode"),
        @JsonSubTypes.Type(value = SourceFolderNode.class,
                name = "com.google.appinventor.shared.rpc.project.SourceFolderNode"),
        @JsonSubTypes.Type(value = YoungAndroidAssetsFolder.class,
                name = "com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidAssetsFolder"),
        @JsonSubTypes.Type(value = YoungAndroidAssetNode.class,
                name = "com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidAssetNode"),
        @JsonSubTypes.Type(value = YoungAndroidComponentsFolder.class,
                name = "com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidComponentsFolder"),
        @JsonSubTypes.Type(value = YoungAndroidFormNode.class,
                name = "com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidFormNode"),
        @JsonSubTypes.Type(value = YoungAndroidBlocksNode.class,
                name = "com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidBlocksNode"),
        @JsonSubTypes.Type(value = YoungAndroidPackageNode.class,
                name = "com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidPackageNode"),
        @JsonSubTypes.Type(value = YoungAndroidSourceFolderNode.class,
                name = "com.google.appinventor.shared.rpc.project.youngandroid.YoungAndroidSourceFolderNode"),
        })
public abstract class ProjectNode {

    private String name;

    private String fileId;

    @JsonIgnore
    private ProjectNode parent;

    private List<ProjectNode> children = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public ProjectNode getParent() {
        return parent;
    }

    public void setParent(ProjectNode parent) {
        this.parent = parent;
    }

    public List<ProjectNode> getChildren() {
        return children;
    }

    public void setChildren(List<ProjectNode> children) {
        this.children = children;
    }
}


package com.google.appinventor.shared.rpc.component;

public class RenameImportedComponentRequest {
    String fullyQualifiedName;
    String newName;
    long projectId;

    public RenameImportedComponentRequest(String fullyQualifiedName, String newName, long projectId) {
        this.fullyQualifiedName = fullyQualifiedName;
        this.newName = newName;
        this.projectId = projectId;
    }
    public RenameImportedComponentRequest() {}
}

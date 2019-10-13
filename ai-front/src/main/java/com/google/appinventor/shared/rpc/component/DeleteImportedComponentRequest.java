package com.google.appinventor.shared.rpc.component;

public class DeleteImportedComponentRequest {
    String fullyQualifiedName;
    long projectId;

    public DeleteImportedComponentRequest(String fullyQualifiedName, long projectId) {
        this.fullyQualifiedName = fullyQualifiedName;
        this.projectId = projectId;
    }

    public DeleteImportedComponentRequest() {}
}

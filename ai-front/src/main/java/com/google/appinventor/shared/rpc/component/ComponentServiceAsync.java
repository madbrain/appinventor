// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2015 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.component;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/component")
public interface ComponentServiceAsync extends RestService {

    @POST
    @Path("/importComponentToProject")
    void importComponentToProject(ImportComponentToProjectRequest request, MethodCallback<ComponentImportResponse> callback);

    @POST
    @Path("/renameImportedComponent")
    void renameImportedComponent(RenameImportedComponentRequest request, MethodCallback<Void> callback);

    @DELETE
    @Path("/deleteImportedComponent")
    void deleteImportedComponent(DeleteImportedComponentRequest request, MethodCallback<Void> callback);
}

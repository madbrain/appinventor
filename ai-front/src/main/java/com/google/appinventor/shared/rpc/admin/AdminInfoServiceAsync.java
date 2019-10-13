// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2015 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.admin;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Interface for the service providing user admin related information.
 */
@Path("/adminInfo")
public interface AdminInfoServiceAsync extends RestService {

    @POST
    @Path("/searchUsers")
    void searchUsers(String startingPoint, MethodCallback<List<AdminUser>> callback);

    @POST
    @Path("/storeUser")
    void storeUser(AdminUser user, MethodCallback<Void> callback);

    @POST
    @Path("/switchUser")
    void switchUser(AdminUser user, MethodCallback<Void> callback);

}

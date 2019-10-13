// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Interface for the service providing the MOTD.  All declarations in this
 * interface are mirrored in {@link GetMotdServiceAsync}.  For further
 * information see {@link GetMotdServiceAsync}.
 *
 * @author kerr@google.com (Debby Wallach)
 */
@Path("/motd")
public interface GetMotdServiceAsync extends RestService {

    @GET
    void getMotd(MethodCallback<Motd> callback);

    @GET
    @Path("/interval")
    void getCheckInterval(MethodCallback<Integer> callback);
}

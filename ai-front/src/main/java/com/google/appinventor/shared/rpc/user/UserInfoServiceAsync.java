// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.user;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;

/**
 * Interface for the service providing user related information. All
 * declarations in this interface are mirrored in {@link UserInfoServiceAsync}.
 * For further information see {@link UserInfoServiceAsync}.
 */
@Path("/userService")
public interface UserInfoServiceAsync extends RestService {

    @GET
    @Path("/systemConfig/{sessionId}")
    void getSystemConfig(@PathParam("sessionId") String sessionId, MethodCallback<Config> callback);

    @GET
    @Path("/userBackpack")
    void getUserBackpack(MethodCallback<String> callback);

    @POST
    @Path("/userBackpack")
    void storeUserBackpack(String backpack, MethodCallback<Void> callback);

//    @GET
//    @Path("/userInformation/{sessionId}")
//    void getUserInformation(@PathParam("sessionId") String sessionId, MethodCallback<User> callback);

    @GET
    @Path("/userById/{userId}")
    void getUserInformationByUserId(@PathParam("userId") String userId, MethodCallback<User> callback);

    @GET
    @Path("/userSettings")
    void loadUserSettings(MethodCallback<String> callback);

    @POST
    @Path("/userSettings")
    void storeUserSettings(String settings, MethodCallback<Void> callback);

    @POST
    @Path("/userName")
    void storeUserName(String name, MethodCallback<Void> callback);

    @POST
    @Path("/userLink")
    void storeUserLink(String link, MethodCallback<Void> callback);

    @POST
    @Path("/userEmailFrequency")
    void storeUserEmailFrequency(Integer emailFrequency, MethodCallback<Void> callback);

    @POST
    @Path("/userFile")
    void hasUserFile(String fileName, MethodCallback<Boolean> callback);

    @DELETE
    @Path("/userFile")
    void deleteUserFile(String fileName, MethodCallback<Void> callback);

    @GET
    @Path("/noop")
    void noop(MethodCallback<Void> callback);

    @GET
    @Path("sharedBackpack/{backPackId}")
    void getSharedBackpack(@PathParam("backPackId") String backPackId, MethodCallback<String> callback);

    @POST
    @Path("sharedBackpack/{backPackId}")
    void storeSharedBackpack(@PathParam("backPackId") String backPackId, String content, MethodCallback<Void> callback);

}

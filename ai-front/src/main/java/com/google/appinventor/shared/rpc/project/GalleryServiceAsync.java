// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.project;

import java.util.List;

import com.google.appinventor.shared.rpc.user.UserInfoServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;

/**
 * Interface for the service providing gallery information.
 */
@Path("/gallery")
public interface GalleryServiceAsync extends RestService {

    @GET
    @Path("/loadGallerySettings")
    void loadGallerySettings(MethodCallback<GallerySettings> callback);

    @POST
    @Path("/publishApp")
    void publishApp(PublishAppRequest request, MethodCallback<GalleryApp> callback);

    @POST
    @Path("/updateApp")
    void updateApp(UpdateAppRequest request, MethodCallback<Void> callback);

//    @POST
//    @Path("/updateAppMetadata")
//    void updateAppMetadata(GalleryApp app, MethodCallback<Void> callback);

//    @POST
//    @Path("/updateAppSource")
//    void updateAppSource(UpdateAppSourceRequest request, MethodCallback<Void> callback);

//    @POST
//    @Path("/indexAll")
//    void indexAll(Integer count, MethodCallback<Void> callback);

    @DELETE
    @Path("/deleteApp/{galleryId}")
    void deleteApp(@PathParam("galleryId") Long galleryId, MethodCallback<java.lang.Void> callback);

    @GET
    @Path("/appWasDownloaded/{galleryId}")
    void appWasDownloaded(@PathParam("galleryId") Long galleryId, MethodCallback<java.lang.Void> callback);

//    @GET
//    @Path("/getNumApps")
//    void getNumApps(MethodCallback<Integer> callback);

    @POST
    @Path("/getRecentApps")
    void getRecentApps(PagedRequest request, MethodCallback<GalleryAppListResult> callback);

    @POST
    @Path("/getFeaturedApp")
    void getFeaturedApp(PagedRequest request, MethodCallback<GalleryAppListResult> callback);

    @POST
    @Path("/getTutorialApp")
    void getTutorialApp(PagedRequest request, MethodCallback<GalleryAppListResult> callback);

    @POST
    @Path("/isFeatured/{galleryId}")
    void isFeatured(@PathParam("galleryId") Long galleryId, MethodCallback<Boolean> callback);

    @POST
    @Path("/isTutorial/{galleryId}")
    void isTutorial(@PathParam("galleryId") Long galleryId, MethodCallback<Boolean> callback);

    @POST
    @Path("/markAppAsFeatured")
    void markAppAsFeatured(Long galleryId, MethodCallback<Boolean> callback);

    @POST
    @Path("/markAppAsTutorial")
    void markAppAsTutorial(Long galleryId, MethodCallback<Boolean> callback);

    @POST
    @Path("/getMostDownloadedApps")
    void getMostDownloadedApps(PagedRequest request, MethodCallback<GalleryAppListResult> callback);

    @POST
    @Path("/getMostLikedApps")
    void getMostLikedApps(PagedRequest request, MethodCallback<GalleryAppListResult> callback);

    @POST
    @Path("/getDeveloperApps")
    void getDeveloperApps(DeveloperAppsRequest request, MethodCallback<GalleryAppListResult> callback);

    @POST
    @Path("/findApps")
    void findApps(FindAppsRequest request, MethodCallback<GalleryAppListResult> callback);

    @GET
    @Path("/getApp/{galleryId}")
    void getApp(@PathParam("galleryId") Long galleryId, MethodCallback<GalleryApp> callback);

    @GET
    @Path("/getComments/{galleryId}")
    void getComments(@PathParam("galleryId") Long galleryId, MethodCallback<List<GalleryComment>> callback);

    @POST
    @Path("/publishComment/{galleryId}")
    void publishComment(@PathParam("galleryId") Long galleryId, String comment, MethodCallback<java.lang.Long> date);

    @POST
    @Path("/increaseLikes/{galleryId}")
    void increaseLikes(@PathParam("galleryId") Long galleryId, MethodCallback<java.lang.Integer> num);

    @POST
    @Path("/decreaseLikes/{galleryId}")
    void decreaseLikes(@PathParam("galleryId") Long galleryId, MethodCallback<java.lang.Integer> num);

    @POST
    @Path("/getNumLikes/{galleryId}")
    void getNumLikes(@PathParam("galleryId") Long galleryId, MethodCallback<java.lang.Integer> num);

    @POST
    @Path("/salvageGalleryApp/{galleryId}")
    void salvageGalleryApp(@PathParam("galleryId") Long galleryId, MethodCallback<java.lang.Void> callback);

    @POST
    @Path("/isLikedByUser/{galleryId}")
    void isLikedByUser(@PathParam("galleryId") Long galleryId, MethodCallback<java.lang.Boolean> bool);

    @POST
    @Path("/addAppReport")
    void addAppReport(AddAppReportRequest request, MethodCallback<java.lang.Long> date);

    @POST
    @Path("/getRecentReports")
    void getRecentReports(PagedRequest request, MethodCallback<GalleryReportListResult> callback);

    @POST
    @Path("/getAllAppReports")
    void getAllAppReports(PagedRequest request, MethodCallback<GalleryReportListResult> callback);

    @POST
    @Path("/isReportedByUser/{galleryId}")
    void isReportedByUser(@PathParam("galleryId") Long galleryId, MethodCallback<java.lang.Boolean> bool);

    @POST
    @Path("/saveAttribution/{galleryId}")
    void saveAttribution(@PathParam("galleryId") Long galleryId, Long attributionId, MethodCallback<java.lang.Long> id);

    @POST
    @Path("/remixedFrom/{galleryId}")
    void remixedFrom(@PathParam("galleryId") Long galleryId, MethodCallback<java.lang.Long> id);

    @POST
    @Path("/remixedTo/{galleryId}")
    void remixedTo(@PathParam("galleryId") Long galleryId, MethodCallback<List<GalleryApp>> apps);

    @POST
    @Path("/markReportAsResolved/{galleryId}")
    void markReportAsResolved(@PathParam("galleryId") Long galleryId, Long reportId, MethodCallback<Boolean> callback);

    @POST
    @Path("/isGalleryAppActivated/{galleryId}")
    void isGalleryAppActivated(@PathParam("galleryId") Long galleryId, MethodCallback<Boolean> callback);

    @POST
    @Path("/deactivateGalleryApp/{galleryId}")
    void deactivateGalleryApp(@PathParam("galleryId") Long galleryId, MethodCallback<Boolean> callback);

    @POST
    @Path("/sendEmail")
    void sendEmail(SendEmailRequest request, MethodCallback<Long> callback);

    @POST
    @Path("/getEmail/{emailId}")
    void getEmail(@PathParam("emailId") Long emailId, MethodCallback<Email> callback);

    @POST
    @Path("/checkIfSendAppStats/{galleryId}")
    void checkIfSendAppStats(@PathParam("galleryId") Long galleryId, CheckIfSendAppStatsRequest request, MethodCallback<Boolean> callback);

    @POST
    @Path("/storeModerationAction/{galleryId}")
    void storeModerationAction(@PathParam("galleryId") Long galleryId, StoreModerationActionRequest request, MethodCallback<Void> callback);

    @POST
    @Path("/getModerationActions")
    void getModerationActions(Long reportId, MethodCallback<List<GalleryModerationAction>> callback);

    @POST
    @Path("/getBlobServingUrl")
    void getBlobServingUrl(String url, MethodCallback<String> callback);
}

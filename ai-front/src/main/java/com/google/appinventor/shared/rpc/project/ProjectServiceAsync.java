// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2017 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.project;

import com.google.appinventor.shared.rpc.RpcResult;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

/**
 * Interface for the service providing project information. All declarations
 * in this interface are mirrored in {@link ProjectServiceAsync}. For further
 * information see {@link ProjectServiceAsync}.
 */
@Path("/project")
public interface ProjectServiceAsync extends RestService {

    @POST
    @Path("/newProject")
    void newProject(NewProjectRequest request, MethodCallback<UserProject> callback);

    @POST
    @Path("/newProjectFromTemplate")
    void newProjectFromTemplate(NewProjectFromTemplateRequest request, MethodCallback<UserProject> callback);

    @POST
    @Path("/newProjectFromExternalTemplate")
    void newProjectFromExternalTemplate(NewProjectFromExternalTemplateRequest request, MethodCallback<UserProject> callback);

    @POST
    @Path("/newProjectFromGallery")
    void newProjectFromGallery(NewProjectFromGalleryRequest request, MethodCallback<UserProject> callback);

    @POST
    @Path("/retrieveTemplateData")
    void retrieveTemplateData(String pathToTemplates, MethodCallback<String> callback);

    @POST
    @Path("/copyProject")
    void copyProject(CopyProjectRequest request, MethodCallback<UserProject> callback);

    @DELETE
    @Path("/deleteProject/{projectId}")
    void deleteProject(@PathParam("projectId") Long projectId, MethodCallback<Void> callback);

    @POST
    @Path("/setGalleryId/{projectId}")
    void setGalleryId(@PathParam("projectId") Long projectId, Long galleryId, MethodCallback<java.lang.Void> callback);

//    @GET
//    @Path("/getProjects")
//    void getProjects(MethodCallback<Long[]> callback);

    @GET
    @Path("/getProjectInfos")
    void getProjectInfos(MethodCallback<List<UserProject>> callback);

    @GET
    @Path("/getProject/{projectId}")
    void getProject(@PathParam("projectId") Long projectId, MethodCallback<ProjectRootNode> callback);

    @GET
    @Path("/loadProjectSettings/{projectId}")
    void loadProjectSettings(@PathParam("projectId") Long projectId, MethodCallback<String> callback);

    @POST
    @Path("/storeProjectSettings")
    void storeProjectSettings(StoreProjectSettingsRequest request, MethodCallback<Void> callback);

    @POST
    @Path("/deleteFile")
    void deleteFile(DeleteFileRequest request, MethodCallback<Long> callback);

//    @POST
//    @Path("/deleteFiles")
//    void deleteFiles(DeleteFilesRequest request, MethodCallback<Long> callback);

    @POST
    @Path("/deleteFolder")
    void deleteFolder(DeleteFolderRequest request, MethodCallback<Long> callback);

    @POST
    @Path("/addFile")
    void addFile(AddFileRequest request, MethodCallback<Long> callback);

    @POST
    @Path("/load")
    void load(LoadRequest request, MethodCallback<String> callback);

    @POST
    @Path("/load2")
    void load2(LoadRequest request, MethodCallback<ChecksumedLoadFile> callback);

    @POST
    @Path("/recordCorruption")
    void recordCorruption(RecordCorruptionRequest request, MethodCallback<Void> callback);

//    @POST
//    @Path("/loadraw")
//    void loadraw(LoadRequest request, MethodCallback<byte[]> callback);

    @POST
    @Path("/loadraw2")
    void loadraw2(LoadRequest request, MethodCallback<String> callback);

    @POST
    @Path("/loadMultiple")
    void load(List<FileDescriptor> files, MethodCallback<List<FileDescriptorWithContent>> callback);

    @POST
    @Path("/save")
    void save(SaveRequest request, MethodCallback<Long> callback);

    @POST
    @Path("/save2")
    void save2(Save2Request request, MethodCallback<Long> callback);

    @POST
    @Path("/saveMultiple")
    void save(SaveMultipleRequest request, MethodCallback<Long> callback);

    @POST
    @Path("/screenshot")
    void screenshot(ScreenshotRequest request, MethodCallback<RpcResult> callback);

    @POST
    @Path("/build")
    void build(BuildRequest request, MethodCallback<RpcResult> callback);

    @POST
    @Path("/getBuildResult")
    void getBuildResult(GetBuildResultRequest request, MethodCallback<RpcResult> callback);

    @POST
    @Path("/importMedia")
    void importMedia(ImportMediaRequest request, MethodCallback<TextFile> odeAsyncCallback);

    @POST
    @Path("/log")
    void log(String message, MethodCallback<Void> callback);

}

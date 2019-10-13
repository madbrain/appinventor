// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.project;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * GallerySettings is the shared class holding gallery settings (enabled, bucket)
 */
public class GallerySettings implements IsSerializable {

    private static final String APPSDIR = "/gallery/apps/";
    private static final String APPSDIRFIXED = "gallery/apps/";
    private static final String USERDIR = "/user/";
    private static final String USERDIRFIXED = "user/";
    private static final String GALLERYPROJECTDIR = "/gallery/projects/"; // we put images here pre-publish
    private static final String GALLERYPROJECTDIRFIXED = "gallery/projects/"; // we put images here pre-publish
    private static final String SOURCEDIR = "/aia";
    private static final String IMAGEDIR = "/image";
    private static final String GCSSERVERURLSTART = "/gs/";
    private static final String GCSCLIENTURLSTARTDEVSERVER = "/gs/";
    private static final String GCSCLIENTURLSTART = "http://storage.googleapis.com/";

    private boolean enabled;
    private String bucket;
    private String environment;
    private String adminEmail;

    /**
     * default constructor
     */
    public GallerySettings() {
        this.enabled = false;
        this.bucket = "";
        this.environment = "";
        this.adminEmail = "";
    }

    /**
     * constructor with given parameters
     *
     * @param enabled
     * @param bucket
     */
    public GallerySettings(boolean enabled, String bucket, String environment, String adminEmail) {
        this.enabled = enabled;
        this.bucket = bucket;
        this.environment = environment;
        this.adminEmail = adminEmail;
    }

    @Override
    public String toString() {
        return this.enabled + "," + this.bucket;
    }

    /**
     * get the gallery bucket
     *
     * @return
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * checck if gallery is enabled
     *
     * @return
     */
    public boolean galleryEnabled() {
        return enabled;
    }

    /**
     * get the gallery environment
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * get the gallery admin email
     */
    public String getAdminEmail() {
        return adminEmail;
    }

    /**
     * get the source url based on given galleryid
     *
     * @param galleryId gallery id
     * @return url of the source
     */
    public String getSourceURL(long galleryId) {
        return GCSSERVERURLSTART + getBucket() + "/" + getSourceKey(galleryId);
    }

    /**
     * get the source key based on given galleryid
     *
     * @param galleryId gallery id
     * @return source key
     */
    public String getSourceKey(long galleryId) {
        return APPSDIRFIXED + galleryId + SOURCEDIR;
    }

    /**
     * get the cloud image url based on given galleryid  (Production Server)
     * should be of form: http://storage.googleapis.com/galleryai2/gallery/apps/4796462844084224/image
     *
     * @param galleryId
     * @return the url of cloud image
     */
    public String getCloudImageURL(long galleryId) {
        return GCSCLIENTURLSTART + getBucket() + APPSDIR + galleryId + IMAGEDIR;
    }

    /**
     * get the cloud image location based on given galleryid  (Development Server)
     *
     * @param galleryId
     * @return the location of cloud image
     */
    public String getCloudImageLocation(long galleryId) {
        return GCSCLIENTURLSTARTDEVSERVER + getBucket() + APPSDIR + galleryId + IMAGEDIR;
    }

    /**
     * get the image url based on given galleryid
     *
     * @param galleryId gallery id
     * @return image url
     */
    public String getImageURL(long galleryId) {
        return GCSSERVERURLSTART + getBucket() + getImageKey(galleryId);
    }

    /**
     * get the image key based on given galleryid
     *
     * @param galleryId gallery id
     * @return image key
     */
    public String getImageKey(long galleryId) {
        return APPSDIRFIXED + galleryId + IMAGEDIR;
    }

    /**
     * get the project image url based on given projectId   (Production Server)
     *
     * @param projectId project id
     * @return url of project image
     */
    public String getProjectImageURL(long projectId) {
        return GCSCLIENTURLSTART + getBucket() + GALLERYPROJECTDIR + projectId + IMAGEDIR;
    }

    /**
     * get the project image location based on given projectId (Development Server)
     *
     * @param location location on Development Server
     * @return location of project image
     */
    public String getProjectImageLocation(long projectId) {
        return GCSCLIENTURLSTARTDEVSERVER + getBucket() + GALLERYPROJECTDIR + projectId + IMAGEDIR;
    }

    /**
     * get the project image path based on given projectId
     *
     * @param projectId project id
     * @return path of the project image
     */
    public String getProjectImagePath(long projectId) {
        return GCSSERVERURLSTART + this.getBucket() + GALLERYPROJECTDIR + projectId + "/image";
    }

    /**
     * get the project image key based on given projectId
     *
     * @param projectId project id
     * @return project image key
     */
    public String getProjectImageKey(long projectId) {
        return GALLERYPROJECTDIRFIXED + projectId + IMAGEDIR;
    }

    /**
     * get the user image key based on given userId
     *
     * @param userId user id
     * @return user image key
     */
    public String getUserImageKey(String userId) {
        return USERDIRFIXED + userId + IMAGEDIR;
    }

    // http://storage.googleapis.com/gallerai2/user/xxxx/image

    /**
     * get the user image url based on given userid   (Production Server)
     *
     * @param userid user id
     * @return url of user image
     */
    public String getUserImageURL(String userid) {
        return GCSCLIENTURLSTART + getBucket() + USERDIR + userid + IMAGEDIR;
    }

    /**
     * get the user image location based on given userid (Development Server)
     *
     * @param location location of image on development server
     * @return location of user image
     */
    public String getUserImageLocation(String userid) {
        return GCSCLIENTURLSTARTDEVSERVER + getBucket() + USERDIR + userid + IMAGEDIR;
    }
}
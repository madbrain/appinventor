// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.web.controllers.user;

/**
 * Data Transfer Object representing user data.
 */
public class User {
    // Unique identifier for the user
    private String userId;

    // user userEmail address
    private String userEmail;

    // user display userName
    private String userName;

    // user introduction userLink
    private String userLink;

    // userEmail notification frequency
    private int userEmailFrequency;

    // whether user has accepted terms of service
    private boolean userTosAccepted;

    // whether the user has admin priviledges
    private boolean isAdmin;

    // If set, we inform the client side to go into read only mode
    // NOTE: isReadOnly is *not* enforced on the server. This is because
    // only privileged users can assert isReadOnly and we assume that they
    // are sufficiently trustworthy that they will not attempt to abuse the
    // system by unsetting it on their client to cause mischief
    private boolean isReadOnly;

    // which type the user has
    private int type;
    private String sessionId;        // Used to ensure only one account active at a time

    private String password;      // Hashed password (if using local login system)

    private String backPackId = null; // If non-null we have a shared backpack

    public final static String usercachekey = "f682688a-1065-4cda-8515-a8bd70200ac9"; // UUID
    // This UUID is prepended to any key lookup for User objects. Memcache is a common
    // key/value store for the entire application. By prepending a unique value, we ensure
    // that other modules that use Memcache will not collide with us. By using a UUID,
    // properly generated, we don't have to worry about allocating specific prefixes and
    // ensuring that they are unique.

    public static final int USER = 0;
    public static final int MODERATOR = 1;
    public static final int DEFAULT_EMAIL_NOTIFICATION_FREQUENCY = 5;

    /**
     * Creates a new user data transfer object.
     *
     * @param id          unique user userId (from {@link com.google.appengine.api.users.User#getUserId()}
     * @param userEmail       user userEmail address
     * @param userTosAccepted TOS accepted?
     * @param sessionId   client session Id
     */
    public User(String id, String userEmail, String userName, String userLink, int userEmailFrequency, boolean userTosAccepted, boolean isAdmin, int type, String sessionId) {
        this.userId = id;
        this.userEmail = userEmail;
        if (userName == null)
            this.userName = getDefaultName();
        else
            this.userName = userName;
        this.userTosAccepted = userTosAccepted;
        this.isAdmin = isAdmin;
        this.userLink = userLink;
        this.userEmailFrequency = userEmailFrequency;
        this.type = type;
        this.sessionId = sessionId;
    }

    private static String getDefaultName(String email) {
        if (email == null)
            return "user";
        String[] parts = email.split("@");
        if (parts.length > 1) {
            return parts[0];
        } else {
            return email;
        }
    }

    private String getDefaultName() {
        return getDefaultName(this.userEmail);
    }

    @SuppressWarnings("unused")
    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLink() {
        return userLink;
    }

    public void setUserLink(String userLink) {
        this.userLink = userLink;
    }

    public int getUserEmailFrequency() {
        return userEmailFrequency;
    }

    public void setUserEmailFrequency(int userEmailFrequency) {
        this.userEmailFrequency = userEmailFrequency;
    }

    public boolean isUserTosAccepted() {
        return userTosAccepted;
    }

    public void setUserTosAccepted(boolean userTosAccepted) {
        this.userTosAccepted = userTosAccepted;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(boolean readOnly) {
        isReadOnly = readOnly;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBackPackId() {
        return backPackId;
    }

    public void setBackPackId(String backPackId) {
        this.backPackId = backPackId;
    }
}

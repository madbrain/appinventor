package com.google.appinventor.web.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class FileData {

    @EmbeddedId
    private ProjectFileId id;

    private RoleEnum role;

    private byte[] content;

    @Column(columnDefinition = "text")
    private String settings;

    private String userId;

    public ProjectFileId getId() {
        return id;
    }

    public void setId(ProjectFileId id) {
        this.id = id;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

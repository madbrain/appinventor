package com.google.appinventor.web.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserProjectId implements Serializable {

    private Long projectId;

    private String userId;

    public UserProjectId(long projectId, String userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    public UserProjectId() {}

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

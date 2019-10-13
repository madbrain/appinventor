package com.google.appinventor.web.services;

public interface BuildStatusService {
    int getBuildStatus(String userId, long projectId);

    void storeBuildStatus(String userId, long projectId, int progress);
}

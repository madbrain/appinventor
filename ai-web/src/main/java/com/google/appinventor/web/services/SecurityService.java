package com.google.appinventor.web.services;

import com.google.appinventor.web.model.UserProjectId;

public interface SecurityService {
    UserProjectId decryptIds(String encryptedUserAndProjectId);

    String encryptUserAndProjectId(String userId, long projectId)
            throws EncryptionException;
}

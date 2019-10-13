package com.google.appinventor.web.test;

import com.google.appinventor.web.model.UserProjectId;
import com.google.appinventor.web.services.SecurityServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SecurityServiceTest {

    @Test
    public void testEncrypt() {
        SecurityServiceImpl securityService = new SecurityServiceImpl("XXXXXXX");
        securityService.iniKey();

        UserProjectId userProjectId = securityService.decryptIds(
                securityService.encryptUserAndProjectId("userId", 1000));

        Assertions.assertThat(userProjectId.getUserId()).isEqualTo("userId");
        Assertions.assertThat(userProjectId.getProjectId()).isEqualTo(1000);
    }
}

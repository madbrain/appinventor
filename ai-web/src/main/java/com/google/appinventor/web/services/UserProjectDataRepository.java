package com.google.appinventor.web.services;

import com.google.appinventor.web.model.UserProjectData;
import com.google.appinventor.web.model.UserProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserProjectDataRepository extends JpaRepository<UserProjectData, UserProjectId> {

    @Query("SELECT upd.id.projectId FROM UserProjectData upd WHERE upd.id.userId = :user")
    List<Long> findProjectIdByUser(@Param("user") String user);
}

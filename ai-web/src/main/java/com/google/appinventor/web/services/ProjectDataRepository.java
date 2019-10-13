package com.google.appinventor.web.services;

import com.google.appinventor.web.model.ProjectData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDataRepository extends JpaRepository<ProjectData, Long> {
}

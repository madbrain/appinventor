package com.google.appinventor.web.services;

import com.google.appinventor.web.model.FileData;
import com.google.appinventor.web.model.ProjectFileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileDataRepository extends JpaRepository<FileData, ProjectFileId> {
    @Query("SELECT f FROM FileData f WHERE f.id.projectId = :projectId")
    List<FileData> findByProject(@Param("projectId") Long projectId);

    @Query("SELECT count(f) FROM FileData f WHERE f.id.projectId = :projectId AND f.id.fileId in (:fileIds)")
    int countFiles(@Param("projectId") Long projectId, @Param("fileIds") List<String> fileIds);

    @Modifying
    @Query("DELETE FROM FileData f WHERE f.id.projectId = :projectId")
    void deleteAllOfProject(@Param("projectId") long projectId);

    @Query("SELECT f.id FROM FileData f WHERE f.id.projectId = :projectId AND f.role = 1")
    List<ProjectFileId> findOutputByProject(@Param("projectId") Long projectId);
}

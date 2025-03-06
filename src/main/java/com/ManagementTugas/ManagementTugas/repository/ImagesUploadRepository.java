package com.ManagementTugas.ManagementTugas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ManagementTugas.ManagementTugas.model.Imageuploading;
import com.fasterxml.jackson.databind.JsonNode;

import io.swagger.v3.core.util.Json;

@Repository
public interface ImagesUploadRepository extends JpaRepository<Imageuploading, String> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO datatask.imageuploading (file_data,filejson, fletype,iddocument) VALUES (:file_data, :filejson, :fletype, :iddocument)", nativeQuery = true)
    void insertFile(@Param("file_data") byte[] file_data, @Param("filejson") Json filejson,
            @Param("fletype") String filetype,
            @Param("iddocument") String iddocument);
}

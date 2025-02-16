package com.ManagementTugas.ManagementTugas.model;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Imageuploading {
    @Id
    private String iddocument;
    private String fletype;

    @Lob
    @Column(columnDefinition = "JSONB")
    private String filejson;

    @Lob
    @Column(columnDefinition = "BYTEA") // Untuk PostgreSQL
    private byte[] fileData;

    public Imageuploading() {
    }

    public String getIddocument() {
        return iddocument;
    }

    public void setIddocument(String iddocument) {
        this.iddocument = iddocument;
    }

    public void setFletype(String fletype) {
        this.fletype = fletype;
    }

    public String getFilejson() {
        return filejson;
    }

    public void setFilejson(String filejson) {
        this.filejson = filejson;
    }

    public String getFletype() {
        return fletype;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

}

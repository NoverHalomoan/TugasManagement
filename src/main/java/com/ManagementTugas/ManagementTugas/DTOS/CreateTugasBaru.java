package com.ManagementTugas.ManagementTugas.DTOS;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Hidden
public record CreateTugasBaru(
                @NotBlank(message = "Nama Tugas Tidak Boleh Kosong") String namatugas,
                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") @NotNull(message = "Tanggal mulai tidak boleh kosong") LocalDateTime mulaitugas,
                @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") @NotNull(message = "Tanggal berakhir tidak boleh kosong") LocalDateTime berakhirtugas,
                @NotBlank(message = "Deskripsi Tugas Tidak Boleh Kosong") String deskripsi,
                @NotNull(message = "Status completed tidak boleh null") Boolean completed,
                @NotBlank(message = "Ide Tugas Tidak Boleh Kosong") String ide_tugas,
                @NotBlank(message = "Id user Tugas Tidak Boleh Kosong") String id_user,
                @NotNull(message = "Id project tidak boleh kosong") Integer id_project

) {

}

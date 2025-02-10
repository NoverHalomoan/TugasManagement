package com.ManagementTugas.ManagementTugas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ManagementTugas.ManagementTugas.DTOS.CreateTugasBaru;
import com.ManagementTugas.ManagementTugas.model.ProjectDTO;
import com.ManagementTugas.ManagementTugas.service.ServiceTask;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Project and Task")

public class TaskController {

    private final ServiceTask serviceTask;
    public static ApiResponseData<Object> apiResponseData = new ApiResponseData<>();

    public TaskController(ServiceTask serviceTask) {
        this.serviceTask = serviceTask;
    }

    @RequestBody(content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n" + //
            "  \"nama\":\"Project Payment\",\r\n" + //
            "  \"description\":\"Project Payment Minchart\"\r\n" + //
            "}")))
    @ApiResponse(responseCode = "200", description = "Valid Request", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
            + //
            "  \"status\": \"200\",\r\n" + //
            "  \"message\": \"Project Sukses Dibuat\",\r\n" + //
            "  \"data\": {\r\n" + //
            "    \"id\": 1,\r\n" + //
            "    \"name\": \"Project Payment\",\r\n" + //
            "    \"description\": \"Project Payment Minchart\",\r\n" + //
            "    \"created_at\": \"2025-02-10T05:52:17.101+00:00\"\r\n" + //
            "  }\r\n" + //
            "}")))
    @PostMapping("/addproject")
    public ResponseEntity<?> addproject(@org.springframework.web.bind.annotation.RequestBody ProjectDTO projectDTO,
            HttpServletRequest httpRequest) {

        return ResponseEntity.status(200).body(serviceTask.addnewproject(httpRequest, projectDTO));
    }

    // add tugas
    @PostMapping("/add-tugas")
    @RequestBody(content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n" + //
            "  \"namatugas\": \"Payment in Minchart\",\r\n" + //
            "  \"mulaitugas\": \"2025-02-10 12:30:00\",\r\n" + //
            "  \"berakhirtugas\": \"2025-02-10 12:30:00\",\r\n" + //
            "  \"deskripsi\":\"Deksrpsi Test\",\r\n" + //
            "  \"completed\":true,\r\n" + //
            "  \"ide_tugas\":\"Membuat Sistem Pembayaran\",\r\n" + //
            "  \"id_user\":\"d2ae8ec7-7558-451a-9dda-db822749d585\",\r\n" + //
            "  \"id_project\":\"1\"\r\n" + //
            "}")))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Valid Request", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ""))),
            @ApiResponse(responseCode = "403", description = "invalid Request", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                    + //
                    "  \"status\": \"400\",\r\n" + //
                    "  \"message\": \"Error Request Tidak Sesuai\",\r\n" + //
                    "  \"data\": {\r\n" + //
                    "    \"ide_tugas\": \"Ide Tugas Tidak Boleh Kosong\"\r\n" + //
                    "  }\r\n" + //
                    "}")))
    })
    public ResponseEntity<?> addTugas(HttpServletRequest httpRequest,
            @Valid @org.springframework.web.bind.annotation.RequestBody CreateTugasBaru createTugasBaru) {

        return ResponseEntity.status(200).body(serviceTask.addnewTask(httpRequest, createTugasBaru));
    }

    // get all project
    @GetMapping("/all-projects")
    public ResponseEntity<?> getallprojects(HttpServletRequest httpRequest) {
        return ResponseEntity.status(200).body(apiResponseData);
    }
}

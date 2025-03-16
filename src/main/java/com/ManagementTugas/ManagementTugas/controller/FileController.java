package com.ManagementTugas.ManagementTugas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ManagementTugas.ManagementTugas.service.ServiceFileCase;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/apiFile")
@AllArgsConstructor
public class FileController {

    private final ServiceFileCase serviceFileCase;


    @PostMapping(path="/excel")
    public ResponseEntity<?> handleexcelfile(@RequestParam("file") MultipartFile file){
        System.out.print("Data Masuk");
        return serviceFileCase.prosesFileExcel(file);
    }

}

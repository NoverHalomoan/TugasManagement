package com.ManagementTugas.ManagementTugas.service;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ManagementTugas.ManagementTugas.PaymentDigital.handlerexception.ResponseWebDigitalPaymnet;
import com.ManagementTugas.ManagementTugas.PaymentDigital.repository.AccountsRepository;
import com.ManagementTugas.ManagementTugas.PaymentDigital.request.DTOAccountRequest;
import com.ManagementTugas.ManagementTugas.PaymentDigital.response.DTOAccountResponse;
import com.ManagementTugas.ManagementTugas.PaymentDigital.service.AccountService;
import com.ManagementTugas.ManagementTugas.controller.ExceptionManagementTugas;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ServiceFileCase {

    private final AccountService accountService;

    public ResponseEntity<?> prosesFileExcel(MultipartFile file) {

        String typefile = file.getOriginalFilename();
        if (file.isEmpty()) {
            throw new ExceptionManagementTugas("File Kososng Tidak Bisa di Proses", HttpStatus.BAD_REQUEST);
        }
        if (!typefile.contains("xls") && !typefile.contains("xlsx")) {
            throw new ExceptionManagementTugas("Tipe File Tidak Sesuai", HttpStatus.BAD_REQUEST);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            String name = "", email = "", password = "", username = "", phone = "";
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {
                        username = cell.toString();
                    }
                    if (cell.getColumnIndex() == 1) {
                        name = cell.toString();
                    }
                    if (cell.getColumnIndex() == 2) {
                        email = cell.toString();
                    }
                    if (cell.getColumnIndex() == 3) {
                        password = cell.toString();
                    }
                    if (cell.getColumnIndex() == 3) {
                        password = cell.toString();
                    }
                    if (cell.getColumnIndex() == 4) {
                        phone = cell.toString();
                    }
                }

                DTOAccountRequest account = DTOAccountRequest.builder().username(username).name(name).email(email).password(password).phonenumber(phone).build();
                int rows = row.getRowNum();
                DTOAccountResponse results = (DTOAccountResponse) accountService.createBased(account).getData();

            }
        } catch (Exception e) {
            throw new ExceptionManagementTugas(e.getMessage(), HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok().body(null);
    }
}

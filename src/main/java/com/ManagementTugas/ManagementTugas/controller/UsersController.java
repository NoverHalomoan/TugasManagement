package com.ManagementTugas.ManagementTugas.controller;

import java.net.http.HttpRequest;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ManagementTugas.ManagementTugas.DTOS.CreateUserDTO;
import com.ManagementTugas.ManagementTugas.model.LoginDTO;
import com.ManagementTugas.ManagementTugas.service.ServiceUser;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@Tag(name = "1. User")
@RequestMapping("/api/tugas-management")
public class UsersController {

        private final ServiceUser serviceUser;
        private static ApiResponseData<Object> apiResponseData = new ApiResponseData<>();

        public UsersController(ServiceUser serviceUser) {
                this.serviceUser = serviceUser;
        }

        @Order(1)
        @PostMapping("/registration")
        @Operation(summary = "1. Registrasi", description = "<b>API Registration Public (Tidak perlu Token untuk mengaksesnya)</b>\n\n\nKetentuan :\n\n1. Parameter request email harus terdapat validasi format email\n2. Parameter request password Length minimal 8 karakter\n3. Handling Response sesuai dokumentasi Response dibawah")
        @RequestBody(content = @Content(examples = @ExampleObject(value = "{\r\n"
                        + //
                        "\"name\":\"VerTersting\",\r\n" + //
                        " \"email\":\"Emailtesting@gmail.com\",\r\n" + //
                        "\"password\":\"Pass123123\"\r\n" + //
                        "}")))
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Request Successfully", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                                        + //
                                        "  \"status\": \"0\",\r\n" + //
                                        "  \"message\": \"Pendaftaran Akun Sukses\",\r\n" + //
                                        "  \"data\": {\r\n" + //
                                        "    \"name\": \"VerTersting\",\r\n" + //
                                        "    \"email\": \"Emailtesting@gmail.com\"\r\n" + //
                                        "  }\r\n" + //
                                        "}"))),
                        @ApiResponse(responseCode = "403", description = "Invalid request", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                                        + //
                                        "  \"status\": \"0\",\r\n" + //
                                        "  \"message\": \"Silahkan Lengkapi Parameter name,email dan password\",\r\n" + //
                                        "  \"data\": null" + //
                                        "}")))
        })
        public ResponseEntity<?> Cretateuser(@RequestHeader Map<String, String> headers,
                        @org.springframework.web.bind.annotation.RequestBody CreateUserDTO createUserDTO) {
                if (headers.get("Content-Type") == "") {
                        apiResponseData.setMessage("Wajib Mengisi Header");
                        apiResponseData.setStatus("403");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseData);
                }
                apiResponseData = serviceUser.createUser(createUserDTO);
                // HttpHeaders httpHeaders = new HttpHeaders();
                // httpHeaders.add("Content-Type", "application/json; charset=utf-8");
                // httpHeaders.add("Content-Length", "60");

                return ResponseEntity.status(Integer.parseInt(apiResponseData.getStatus()))
                                .header("Content-Type", "application/json; charset=utf-8")
                                .body(apiResponseData);
        }

        @PostMapping(path = "/creates")
        public ResponseEntity<?> createUserNew(CreateUserDTO createUserDTO) {
                
                apiResponseData.setMessage("Wajib Mengisi Header");
                apiResponseData.setStatus("403");
                return ResponseEntity.status(HttpStatus.OK).body(apiResponseData);
        }

        // For Login
        @Order(2)
        @PostMapping("/login")
        @Operation(summary = "2. Login User", description = "<b>API Login Public (Tidak perlu Token untuk mengaksesnya)</b>\n\n\nDigunakan untuk melakukan login dan mendapatkan authentication berupa JWT (Json Web Token)\n\nKetentuan :\n\n1. Parameter request email harus terdapat validasi format email\n2. Parameter request password Length minimal 8 karakter"
                        + //
                        "\n3. <b>JWT</b> yang digenerate harus memuat payload email dan di set <b>expiration</b> selama 12 jam dari waktu di generate\n4. Handling Response sesuai dokumentasi Response dibawah")
        @RequestBody(content = @Content(examples = @ExampleObject(value = "{\r\n" + //
                        " \"email\":\"Emailtesting@gmail.com\",\r\n" + //
                        "\"password\":\"Pass123123\"\r\n" + //
                        "}")))
        // @BodyResponsesApi(successresponse = ApiResponseData.class, errorresponse =
        // ApiResponseData.class)
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Request Successfully", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                                        + //
                                        "    \"status\": \"200\",\r\n" + //
                                        "    \"message\": \"Berhasil Login !\",\r\n" + //
                                        "    \"data\": {\r\n" + //
                                        "        \"token\": \"ZTMzNzA0YWYtOTUwNC00OGVhLWFkNTMtOTNlYmVjNmQzMjE3LjE3Mzg5MzM5NzU=\",\r\n"
                                        + //
                                        "        \"username\": \"Emailtesting@gmail.com\"\r\n" + //
                                        "    }\r\n" + //
                                        "}"))),
                        @ApiResponse(responseCode = "403", description = "Invalid request", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                                        + //
                                        "    \"status\": \"403\",\r\n" + //
                                        "    \"message\": \"Email dan Password Salah\",\r\n" + //
                                        "    \"data\": null\r\n" + //
                                        "}")))
        })
        public ResponseEntity<?> loginaplication(@RequestHeader Map<String, String> headers,
                        @Valid @org.springframework.web.bind.annotation.RequestBody LoginDTO loginDTO,
                        HttpServletResponse response) {
                if (headers.get("Content-Type") == "" || headers.isEmpty()) {
                        apiResponseData.setMessage("Wajib Mengisi Header");
                        apiResponseData.setStatus("403");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseData);
                }
                apiResponseData = serviceUser.LoginAplication(loginDTO, response);

                return ResponseEntity.status(Integer.parseInt(apiResponseData.getStatus())).body(apiResponseData);
        }

        
        @Hidden
        @GetMapping("/validasitoken")
        public ResponseEntity<?> validasitokens(HttpServletRequest request) {
                apiResponseData = serviceUser.validasitokens(request);
                return ResponseEntity.status(200).body(apiResponseData);
        }

        // @Operation(parameters = @Parameter(in = ParameterIn.HEADER))
        @RequestBody(content = @Content(mediaType = "application/json"))
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Request Success", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                                        + //
                                        "  \"status\": \"200\",\r\n" + //
                                        "  \"message\": \"Sukses\",\r\n" + //
                                        "  \"data\": {\r\n" + //
                                        "    \"name\": \"VerTersting\",\r\n" + //
                                        "    \"email\": \"Emailtesting@gmail.com\",\r\n" + //
                                        "    \"password\": \"Pass123\"\r\n" + //
                                        "  }\r\n" + //
                                        "}"))),
                        @ApiResponse(responseCode = "403", description = "Invalid Request", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\n"
                                        + //
                                        "  \"status\": 108,\n" + //
                                        "  \"message\": \"Token tidak tidak valid atau kadaluwarsa\",\n" + //
                                        "  \"data\": null\n" + //
                                        "}")))
        })

        // get profile
        @Order(3)
        @Operation(summary = "3. Profile User", description = "Memerlukan Token untuk mengakses module ini")
        @GetMapping("/profile")
        public ResponseEntity<?> Cgetprofiledetail(@RequestHeader Map<String, String> headers,
                        HttpServletRequest request) {
                apiResponseData = serviceUser.getdetailprofile(request);
                // HttpHeaders httpHeaders = new HttpHeaders();
                // httpHeaders.add("Content-Type", "application/json; charset=utf-8");
                // httpHeaders.add("Content-Length", "60");
                // ResponseEntity<Object> responseEntity = new
                // ResponseEntity<>(apiResponseData,httpHeaders,200);

                return ResponseEntity.status(Integer.parseInt(apiResponseData.getStatus())).body(apiResponseData);
        }

        @RequestBody(content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n" + //
                        "  \"nama\":\"Updateing Testing\",\r\n" + //
                        " \"email\":\"Emailtestinggmail.com\",\r\n" + //
                        "\"password\":\"Pass123\"\r\n" + //
                        "}")))
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Request Valid", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                                        + //
                                        "  \"status\": \"403\",\r\n" + //
                                        "  \"message\": \"Token tidak tidak valid atau kadaluwarsa\",\r\n" + //
                                        "  \"data\": {\r\n" + //
                                        "  \"nama\":\"Updateing Testing\",\r\n" + //
                                        " \"email\":\"Emailtestinggmail.com\",\r\n" + //
                                        "\"password\":\"Pass123\"\r\n" + //
                                        "}\r\n" + //
                                        "}"))),
                        @ApiResponse(responseCode = "403", description = "Request Invalid", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                                        + //
                                        "  \"status\": \"403\",\r\n" + //
                                        "  \"message\": \"Token tidak tidak valid atau kadaluwarsa\",\r\n" + //
                                        "  \"data\": null\r\n" + //
                                        "}")))
        })

        // profile update
        @Order(4)
        @Operation(summary = "4. Update Profile", description = "Memerlukan login user untuk mengakses update profile")
        @PostMapping("/profile/update")
        public ResponseEntity<?> Dupdatedetailprofile(
                        @Valid @org.springframework.web.bind.annotation.RequestBody CreateUserDTO createUserDTO,
                        HttpServletRequest request) {
                apiResponseData = serviceUser.updateuserprofile(createUserDTO, request);
                return ResponseEntity.status(200).body(apiResponseData);
        }

        // get all data users
        @Operation(summary = "5. Get All Users", description = "Memerlukan token untuk mengakses Aoi ini")
        @RequestBody(content = @Content(mediaType = "application/json"))
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Valid Request", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                                        + //
                                        "  \"status\": \"200\",\r\n" + //
                                        "  \"message\": \"Data all Users\",\r\n" + //
                                        "  \"data\": [\r\n" + //
                                        "    {\r\n" + //
                                        "      \"name\": \"VerTersting\",\r\n" + //
                                        "      \"email\": \"Emailtesting@gmail.com\",\r\n" + //
                                        "      \"password\": \"Pass123\",\r\n" + //
                                        "      \"profile_image\": null\r\n" + //
                                        "    },\r\n" + //
                                        "    {\r\n" + //
                                        "      \"name\": \"VerTersting2\",\r\n" + //
                                        "      \"email\": \"Emailtesting2@gmail.com\",\r\n" + //
                                        "      \"password\": \"Pass123123\",\r\n" + //
                                        "      \"profile_image\": null\r\n" + //
                                        "    }\r\n" + //
                                        "  ]\r\n" + //
                                        "}"))),
                        @ApiResponse(responseCode = "403", description = "Request Invalid", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\r\n"
                                        + //
                                        "  \"status\": \"403\",\r\n" + //
                                        "  \"message\": \"Token tidak tidak valid atau kadaluwarsa\",\r\n" + //
                                        "  \"data\": null\r\n" + //
                                        "}")))
        })
        @GetMapping("/allusers")
        public ResponseEntity<?> getallusers(HttpServletRequest httpRequest) {

                apiResponseData = serviceUser.getalldatausers(httpRequest);
                return ResponseEntity.status(200).body(apiResponseData);
        }

        @PutMapping("/upload/profile")
        public ResponseEntity<?> updateprofileimage(HttpServletRequest httpServletRequest,
                        @RequestParam("File") MultipartFile multipartFile) {
                try {
                        apiResponseData = serviceUser.upload_fileprofile(httpServletRequest, multipartFile);
                } catch (Exception e) {
                        apiResponseData.setMessage("Gagal Memproses File");
                        apiResponseData.setStatus("200");
                        apiResponseData.setData(e.getMessage());
                }

                return ResponseEntity.status(200).body(apiResponseData);
        }

}

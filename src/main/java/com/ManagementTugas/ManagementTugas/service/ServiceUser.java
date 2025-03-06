package com.ManagementTugas.ManagementTugas.service;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.ManagementTugas.ManagementTugas.DTOS.CreateUserDTO;
import com.ManagementTugas.ManagementTugas.DTOS.MapperUser;
import com.ManagementTugas.ManagementTugas.DTOS.ResponseLoginDTO;
import com.ManagementTugas.ManagementTugas.controller.ApiResponseData;
import com.ManagementTugas.ManagementTugas.model.Imageuploading;
import com.ManagementTugas.ManagementTugas.model.LoginDTO;
import com.ManagementTugas.ManagementTugas.model.Users;
import com.ManagementTugas.ManagementTugas.repository.CustomeRepository;
import com.ManagementTugas.ManagementTugas.repository.ImagesUploadRepository;
import com.ManagementTugas.ManagementTugas.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;

import io.swagger.v3.core.util.Json;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Service
@Validated
public class ServiceUser {
    private final UserRepository userRepository;
    private final CustomeRepository customeRepository;
    private final MapperUser mapperUser;
    private final GeneratedToken generatedToken;
    private final ImagesUploadRepository imagesUploadRepository;
    private static ApiResponseData<Object> apiResponseData = new ApiResponseData<>();

    private static final Logger log = LoggerFactory.getLogger(ServiceTask.class);

    public ServiceUser(UserRepository userRepository, MapperUser mapperUser, GeneratedToken generatedToken,
            CustomeRepository customeRepository, ImagesUploadRepository imagesUploadRepository) {
        this.userRepository = userRepository;
        this.mapperUser = mapperUser;
        this.generatedToken = generatedToken;
        this.customeRepository = customeRepository;
        this.imagesUploadRepository = imagesUploadRepository;
    }

    // for login
    public ApiResponseData<Object> LoginAplication(@Valid LoginDTO loginDTO, HttpServletResponse responses) {
        apiResponseData = new ApiResponseData<>();
        LoginDTO reallogindto = new LoginDTO();
        try {
            reallogindto.setEmail(loginDTO.getEmail());
            reallogindto.setPassword(loginDTO.getPassword());
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        Optional<Users> userlogin = userRepository.findByemail(loginDTO.getEmail());
        if (userlogin.isPresent()) {
            if (loginDTO.getPassword().matches(userlogin.get().getPassword())) {
                String tokenlogin = GeneratedToken.generatedTokenTime(60);
                ResponseLoginDTO responseLoginDTO = new ResponseLoginDTO(tokenlogin, userlogin.get().getEmail());
                apiResponseData.setStatus("200");
                apiResponseData.setMessage("Berhasil Login !");
                apiResponseData.setData(responseLoginDTO);
                userlogin.get().setTokenlogin(tokenlogin);
                userRepository.save(userlogin.get());
                generatedToken.saveTokenOnlyCoocies(tokenlogin, responses);

            } else {
                apiResponseData.setStatus("403");
                apiResponseData.setMessage("Email dan Password Salah");
                apiResponseData.setData(null);
            }
        } else {
            apiResponseData.setStatus("403");
            apiResponseData.setMessage("Email dan Password Salah");
            apiResponseData.setData(null);
        }

        return apiResponseData;

    }

    // for create users
    public ApiResponseData<Object> createUser(CreateUserDTO createUserDTO) {
        // chek data all
        if (createUserDTO.getEmail() == "" || createUserDTO.getName() == "" || createUserDTO.getPassword() == "") {
            apiResponseData.setMessage("Silahkan Lengkapi Parameter name,email dan password");
            apiResponseData.setStatus("403");
            apiResponseData.setData(null);
            return apiResponseData;
        }

        if (userRepository.existsByemail(createUserDTO.getEmail())) {
            apiResponseData.setMessage("Silahkan Login pakai email yang sudah terdaftar");
            apiResponseData.setStatus("403");
            apiResponseData.setData(Map.of(
                    "name", createUserDTO.getName(),
                    "email", createUserDTO.getEmail()));
        } else {
            String tokenid = generatedToken.generatedTokenid();
            Users usernew = userRepository.save(mapperUser.tousers(tokenid, createUserDTO));
            if (!usernew.getIduser().isEmpty()) {
                apiResponseData.setMessage("Pendaftaran Akun Sukses");
                apiResponseData.setStatus("200");
                apiResponseData.setData(Map.of(
                        "name", createUserDTO.getName(),
                        "email", createUserDTO.getEmail()));
            } else {
                apiResponseData.setMessage("The server is busy. Please try again later.");
                apiResponseData.setStatus("403");
                apiResponseData.setData(usernew);
            }
        }

        return apiResponseData;
    }

    // Just Checkin Validasi of token
    public ApiResponseData<Object> validasitokens(HttpServletRequest request) {
        if (generatedToken.adatokendata(request) == 0) {
            apiResponseData.setMessage("Token tidak tidak valid atau kadaluwarsa");
            apiResponseData.setStatus("403");
            apiResponseData.setData(null);
        } else {
            apiResponseData.setMessage("Sukses Login");
            apiResponseData.setStatus("200");
            apiResponseData.setData(null);
        }
        return apiResponseData;
    }

    // for get detail profile
    public ApiResponseData<Object> getdetailprofile(HttpServletRequest request) {
        apiResponseData = new ApiResponseData<>();
        String tokendata = GeneratedToken.gettokenvalidasi(request);
        if (tokendata == "") {
            apiResponseData.setMessage("Token tidak tidak valid atau kadaluwarsa");
            apiResponseData.setStatus("403");
            apiResponseData.setData(null);
        }
        Optional<Users> findusers = customeRepository.findByTokenlogin(tokendata);
        apiResponseData.setMessage("Sukses");
        apiResponseData.setStatus("200");
        findusers.get().setTokenlogin(null);
        apiResponseData.setData(findusers);
        return apiResponseData;
    }

    // get detail user and update data
    public ApiResponseData<Object> updateuserprofile(CreateUserDTO createUserDTO, HttpServletRequest request) {
        String tokendata = GeneratedToken.gettokenvalidasi(request);
        Optional<Users> usersData = customeRepository.findByTokenlogin(tokendata);
        if (tokendata.isEmpty() || tokendata.isBlank() || tokendata == "") {
            apiResponseData.setMessage("Token tidak tidak valid atau kadaluwarsa");
            apiResponseData.setStatus("403");
            apiResponseData.setData(null);
        } else {
            usersData.get()
                    .setName(createUserDTO.getName().isEmpty() ? usersData.get().getName() : createUserDTO.getName());
            usersData.get()
                    .setEmail(
                            createUserDTO.getEmail().isEmpty() ? usersData.get().getEmail() : createUserDTO.getEmail());
            usersData.get().setPassword(
                    createUserDTO.getPassword().isEmpty() ? usersData.get().getPassword()
                            : createUserDTO.getPassword());
            Users returnuser = userRepository.save(usersData.get());
            apiResponseData.setMessage("Data Berhasil di Update");
            apiResponseData.setStatus("200");
            apiResponseData.setData(returnuser);
        }
        return apiResponseData;
    }

    // for get all data users
    public ApiResponseData<Object> getalldatausers(HttpServletRequest httpServletRequest) {
        if (generatedToken.adatokendata(httpServletRequest) == 0) {
            apiResponseData.setMessage("Token tidak tidak valid atau kadaluwarsa");
            apiResponseData.setStatus("403");
            apiResponseData.setData(null);

            return apiResponseData;
        }
        String tokendata = GeneratedToken.gettokenvalidasi(httpServletRequest);
        if (tokendata.isEmpty() || tokendata.isBlank() || tokendata == "") {
            apiResponseData.setMessage("Token tidak tidak valid atau kadaluwarsa");
            apiResponseData.setStatus("403");
            apiResponseData.setData(null);
        } else {
            List<Users> allusers = userRepository.findAll();
            apiResponseData.setMessage("Data all Users");
            apiResponseData.setStatus("200");
            apiResponseData.setData(allusers);
        }

        return apiResponseData;
    }

    private JsonNode createJsonWithBase64(String base64File) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();
        jsonNode.put("file", base64File); // Menyimpan file dalam bentuk Base64
        return jsonNode;

    }

    public ApiResponseData<Object> upload_fileprofile(HttpServletRequest httpServletRequest,
            MultipartFile multipartFile) {
        try {
            System.out.println(multipartFile.getBytes());
            byte[] datafile = multipartFile.getBytes();
            String jsonstring = new String(datafile);
            String base64File = Base64.getEncoder().encodeToString(datafile);
            // JSONPObject jsonpObject = new JSONPObject("FileJSON", base64File);
            // StringB jsonpObject = (String) new JSONPObject("FileJSON",
            // base64File).getValue();
            // String iddokument = generatedToken.generatedimageid();
            Imageuploading imageuploadings = new Imageuploading();
            JsonNode fileJson = createJsonWithBase64(base64File);
            Json FTJSAN = new Json();
            System.out.println(fileJson);
            // imageuploadings.setFilejson(base64File);
            // imageuploadings.setFletype(multipartFile.getContentType());
            // imageuploadings.setIddocument("1233333");
            try {
                imagesUploadRepository.insertFile(datafile, FTJSAN, multipartFile.getContentType(), "11111ww");
            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {

        }
        return apiResponseData;
    }

    // get all data users by common
    public List<Users> show_findalldatauser() {
        return userRepository.findAll();
    }

    // update user personal
    public List<Users> update_userpersonal(String iduser, String name, boolean activestatus) {
        Users users = userRepository.findByiduser(iduser).get();
        users.setName(name);
        users.setActivestatus(activestatus);
        userRepository.save(users);
        return userRepository.findAll();

    }

    // delete user
    public List<Users> delete_userpersonal(String iduser) {
        Users users = userRepository.findByiduser(iduser).get();
        userRepository.delete(users);
        return userRepository.findAll();

    }
}

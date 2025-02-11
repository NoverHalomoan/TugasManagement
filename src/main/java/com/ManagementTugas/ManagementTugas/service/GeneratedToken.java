package com.ManagementTugas.ManagementTugas.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.Year;
import java.util.Base64;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ManagementTugas.ManagementTugas.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GeneratedToken {

    private UserRepository userRepository;

    private final JdbcTemplate jdbcTemplate;

    public GeneratedToken(UserRepository userRepository, JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public String generatedTokenid() {
        String id;
        do {
            id = UUID.randomUUID().toString();
        } while (userRepository.findByiduser(id).isPresent());
        return id;

    }

    public String generatedTokenLogin(String iduser) {
        String token;

        do {
            try {
                token = hashSHA256(iduser);
            } catch (Exception e) {
                token = e.getMessage();
            }
        } while (token == "");
        return token;
    }

    // Algoritma hashSHA256
    private String hashSHA256(String logins) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(logins.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    // khusus generated Token with validation
    public static String generatedTokenTime(int expirationMinute) {
        if (expirationMinute <= 0) {
            expirationMinute = 30;
        }
        String uniqueID = UUID.randomUUID().toString();
        long expiryTime = Instant.now().plusSeconds(expirationMinute * 60).getEpochSecond();

        String token = uniqueID + "." + expiryTime;
        return Base64.getEncoder().encodeToString(token.getBytes());
    }

    public static String updatetimetoken(String token) {
        String descode = new String(Base64.getDecoder().decode(token));
        String[] parts = descode.split("\\.");
        long expiryTime = Instant.now().plusSeconds(30 * 60).getEpochSecond();
        String newtoken = parts[0] + "." + expiryTime;
        return Base64.getEncoder().encodeToString(newtoken.getBytes());
    }

    public static String isTokenValid(String token) {

        String Hasil = "";
        try {
            String decode = new String(Base64.getDecoder().decode(token));

            String[] parts = decode.split("\\.");
            long exoiredTime = Long.parseLong(parts[1]);
            if (exoiredTime > Instant.now().getEpochSecond()) {
                Hasil = token;
            }
        } catch (Exception e) {
            Hasil = "";
        }
        return Hasil;

    }

    // save token in localstorage

    public void saveTokenOnlyCoocies(String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("authToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(36000);
        response.addCookie(cookie);
    }

    public Integer adatokendata(HttpServletRequest response) {
        System.out.println("Get Cocies");
        if (response.getCookies() == null) {
            return 0;
        }

        Cookie[] cookies = response.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().matches("authToken")) {
                return userRepository.findByTokenlogin(cookie.getValue()).isPresent() ? 1 : 0;
            }
        }
        return 0;
    }

    public static String gettokenvalidasi(HttpServletRequest response) {
        Cookie[] cookies = response.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().matches("authToken")) {
                return isTokenValid(cookie.getValue());
            }
        }
        return "";
    }

    // Generated ID for tugas
    public String generatedIdTugas() {
        int years = Year.now().getValue() % 100;
        Long nextval = jdbcTemplate.queryForObject("select nextval('datatask.custom_id_seq')", Long.class);
        String sequenceformat = String.format("%08d", nextval);

        return "T" + years + sequenceformat;
    }

}

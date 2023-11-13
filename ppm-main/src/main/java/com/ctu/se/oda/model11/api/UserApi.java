package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.daos.UserDAO;
import com.ctu.se.oda.model11.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(name = "Authorization") String authorizationHeader) {
        try {
            String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded);

            // Tách tên người dùng và mật khẩu từ thông tin xác thực giải mã
            final String[] values = credentials.split(":", 2);
            String username = values[0];

            String fileUser = "E:\\LuanVan\\Code\\ppm-service-0.1.0\\ppm-service\\ppm-main\\src\\main\\java\\com\\ctu\\se\\oda\\model11\\data\\user.txt";
            List<User> users = userDAO.getUsersFromFile(fileUser);

            for (User user : users) {
                if (user.getUserInfo().getUsername().equals(username)) {
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

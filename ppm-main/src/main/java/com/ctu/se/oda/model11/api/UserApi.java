package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.daos.UserDAO;
import com.ctu.se.oda.model11.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(name = "Authorization") String authorizationHeader) {
        String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded);

        final String[] values = credentials.split(":", 2);
        String username = values[0];

        List<User> users = userDAO.getUsersFromFile();

        for (User user : users) {
            if (user.getUserInfo().getUsername().equals(username)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

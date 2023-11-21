package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.daos.UserDAO;
import com.ctu.se.oda.model11.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

        Optional<User> authenticatedUser = users.stream()
                .filter(user -> user.getUserInfo().getUsername().equals(username))
                .findFirst();

        return authenticatedUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

}

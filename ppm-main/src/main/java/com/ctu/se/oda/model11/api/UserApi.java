package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.UserApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserApplication userApplication;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(name = "Authorization") String authorizationHeader) {

        return new ResponseEntity<>(userApplication.login(authorizationHeader), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        return new ResponseEntity<>(userApplication.getUser(UUID.fromString(userId)), HttpStatus.OK);
    }
}

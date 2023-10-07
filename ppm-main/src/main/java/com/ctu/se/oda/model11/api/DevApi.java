package com.ctu.se.oda.model11.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dev")
public class DevApi {

    @GetMapping("/test")
    public ResponseEntity<String> callTest() {
        return new ResponseEntity<String>("Application is ready to serve", HttpStatus.OK);
    }
}

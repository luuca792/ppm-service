package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.models.dtos.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserApi {

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestHeader(name = "Authorization") String authorizationHeader) {

        try {
            String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded);

            final String[] values = credentials.split(":", 2);
            String username = values[0];

            Resource resource = new ClassPathResource("data/user.txt");
            InputStream inputStream = resource.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            List<UserDTO> users = Arrays.asList(objectMapper.readValue(inputStream, UserDTO[].class));

            Optional<UserDTO> authenticatedUser = users.stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst();

            return authenticatedUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
        }
        catch (IOException e) {
            throw new RuntimeException("Error reading user file", e);
        }
    }

}

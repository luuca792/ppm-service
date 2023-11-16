package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
@Validated
public class UserDAO {
    public List<User> getUsersFromFile() {
        try {
            Resource resource = new ClassPathResource("data/user.txt");
            InputStream inputStream = resource.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            return Arrays.asList(objectMapper.readValue(inputStream, User[].class));
        } catch (IOException e) {
            throw new RuntimeException("Error reading user file", e);
        }
    }
}

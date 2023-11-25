package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.clients.UserAuthenicationClient;
import com.ctu.se.oda.model11.interfaces.IUserApplication;
import com.ctu.se.oda.model11.models.dtos.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@NoArgsConstructor
public class UserApplication implements IUserApplication {

    @Autowired
    private UserAuthenicationClient userAuthenicationClient;

    @Override
    public UserDTO login(String basicAuth) {
        return userAuthenicationClient.login(basicAuth);
    }

    @Override
    public UserDTO getUser(UUID userId) {
        return userAuthenicationClient.getUserById(userId);
    }
}

package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.clients.UserAuthenticationClient;
import com.ctu.se.oda.model11.interfaces.IUserApplication;
import com.ctu.se.oda.model11.models.dtos.UserDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class UserApplication implements IUserApplication {

    @Autowired
    private UserAuthenticationClient userAuthenticationClient;

    @Override
    public UserDTO login(String basicAuth) {
        return userAuthenticationClient.login(basicAuth);
    }

    @Override
    public UserDTO getUser(UUID userId) {
        return userAuthenticationClient.getUserById(userId);
    }
}

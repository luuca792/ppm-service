package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.dtos.UserDTO;

import java.util.UUID;

public interface IUserApplication {
    UserDTO login(String basicAuth);
    UserDTO getUser(UUID userId);
}

package com.ctu.se.oda.model11.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String accessToken;
    private UUID userId;
    private String username;
    private String role;
}

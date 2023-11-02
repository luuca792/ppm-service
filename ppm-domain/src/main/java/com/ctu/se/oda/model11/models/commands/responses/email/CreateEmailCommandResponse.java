package com.ctu.se.oda.model11.models.commands.responses.email;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateEmailCommandResponse {
    private UUID emailId;

    private String email;

    public CreateEmailCommandResponse(UUID emailId, String email) {
        this.emailId = emailId;
        this.email = email;
    }
}

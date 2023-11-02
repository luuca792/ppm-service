package com.ctu.se.oda.model11.models.commands.requests.email;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class UpdateEmailCommandRequest {

    private UUID emailId;

    private String email;

    public UpdateEmailCommandRequest(UUID emailId, String email) {
        this.emailId = emailId;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UpdateEmailCommandRequest{" +
                "emailId='" + emailId +
                "email='" + email + '\'';
    }
}

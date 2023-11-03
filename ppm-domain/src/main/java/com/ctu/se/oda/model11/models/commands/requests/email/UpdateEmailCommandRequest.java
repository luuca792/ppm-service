package com.ctu.se.oda.model11.models.commands.requests.email;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class UpdateEmailCommandRequest {

    private UUID emailId;

    @NotNull
    @Size(max = 255)
    private String emailAddress;

    public UpdateEmailCommandRequest(UUID emailId, String emailAddress) {
        this.emailId = emailId;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "UpdateEmailCommandRequest{" +
                "emailId='" + emailId +
                "emailAddress='" + emailAddress +
                '\'';
    }
}

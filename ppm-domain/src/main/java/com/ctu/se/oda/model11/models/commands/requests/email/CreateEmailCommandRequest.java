package com.ctu.se.oda.model11.models.commands.requests.email;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class CreateEmailCommandRequest {

    @NotNull
    @Size(max = 255)
    private String emailAddress;

    public CreateEmailCommandRequest(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "CreateEmailCommandRequest{" +
                "emailAddress='" + emailAddress +
                '\'';
    }
}

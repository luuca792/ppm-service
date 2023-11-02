package com.ctu.se.oda.model11.models.commands.requests.email;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CreateEmailCommandRequest {

    @NotNull
    @Size(max = 255)
    private String email;

    public CreateEmailCommandRequest(@JsonProperty("email") String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CreateEmailCommandRequest{" +
                "email='" + email + '\'';
    }
}

package com.ctu.se.oda.model11.models.commands.requests.email;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class CreateEmailCommandRequest {

    @NotBlank
    private String email;

    public CreateEmailCommandRequest(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CreateEmailCommandRequest{" +
                "email='" + email + '\'';
    }
}

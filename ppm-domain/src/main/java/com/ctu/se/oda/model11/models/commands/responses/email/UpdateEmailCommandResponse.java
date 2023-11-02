package com.ctu.se.oda.model11.models.commands.responses.email;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class UpdateEmailCommandResponse {
    private UUID emailId;

    private String email;

    public UpdateEmailCommandResponse(UUID emailId, String email) {
        this.emailId = emailId;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateEmailCommandResponse that = (UpdateEmailCommandResponse) o;
        return  emailId.equals(that.emailId) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, email);
    }
}

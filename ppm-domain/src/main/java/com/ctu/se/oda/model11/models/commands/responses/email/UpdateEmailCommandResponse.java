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

    private String emailAddress;

    public UpdateEmailCommandResponse(UUID emailId, String emailAddress) {
        this.emailId = emailId;
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateEmailCommandResponse that = (UpdateEmailCommandResponse) o;
        return  emailId.equals(that.emailId) &&
                emailAddress.equals(that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailId, emailAddress);
    }
}

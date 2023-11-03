package com.ctu.se.oda.model11.models.queries.responses.email;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class RetrieveEmailQueryResponse {

    private UUID emailId;

    private String emailAddress;

    public RetrieveEmailQueryResponse(UUID emailId, String emailAddress) {
        this.emailId = emailId;
        this.emailAddress = emailAddress;
    }
}

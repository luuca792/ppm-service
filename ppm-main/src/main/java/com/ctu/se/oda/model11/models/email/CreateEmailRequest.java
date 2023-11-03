package com.ctu.se.oda.model11.models.email;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEmailRequest {
    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonCreator
    public CreateEmailRequest(@JsonProperty("emailAddress") String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

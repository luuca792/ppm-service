package com.ctu.se.oda.model11.models.email;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CreateEmailRequest {
    @JsonProperty
    private String email;
}

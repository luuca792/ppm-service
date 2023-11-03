package com.ctu.se.oda.model11.models.email;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateEmailRequest {
    private String emailId;

    private String emailAddress;
}

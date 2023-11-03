package com.ctu.se.oda.model11.mappers.email;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.email.UpdateEmailCommandRequest;
import com.ctu.se.oda.model11.models.email.UpdateEmailRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class UpdateEmailRequestMapper implements IMainMapper<UpdateEmailRequest, UpdateEmailCommandRequest> {
    @Override
    public UpdateEmailCommandRequest convert(UpdateEmailRequest source) {
        return UpdateEmailCommandRequest.builder()
                .emailId(UUID.fromString(source.getEmailId()))
                .emailAddress(source.getEmailAddress())
                .build();
    }

    @Override
    public UpdateEmailRequest reverse(UpdateEmailCommandRequest destination) {
        return UpdateEmailRequest.builder()
                .emailId(destination.getEmailId().toString())
                .emailAddress(destination.getEmailAddress())
                .build();
    }
}

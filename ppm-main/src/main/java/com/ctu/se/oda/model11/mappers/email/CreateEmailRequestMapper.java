package com.ctu.se.oda.model11.mappers.email;

import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.email.CreateEmailCommandRequest;
import com.ctu.se.oda.model11.models.email.CreateEmailRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@NoArgsConstructor
public class CreateEmailRequestMapper implements IMainMapper<CreateEmailRequest, CreateEmailCommandRequest> {
    @Override
    public CreateEmailCommandRequest convert(CreateEmailRequest source) {
        return CreateEmailCommandRequest.builder()
                .emailAddress(source.getEmailAddress())
                .build();
    }

    @Override
    public CreateEmailRequest reverse(CreateEmailCommandRequest destination) {
        return CreateEmailRequest.builder()
                .emailAddress(destination.getEmailAddress())
                .build();
    }
}

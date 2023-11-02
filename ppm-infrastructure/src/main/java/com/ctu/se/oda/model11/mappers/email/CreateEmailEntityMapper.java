package com.ctu.se.oda.model11.mappers.email;

import com.ctu.se.oda.model11.entities.Email;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.email.CreateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.email.CreateEmailCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CreateEmailEntityMapper implements IInfrastructureMapper<CreateEmailCommandRequest, Email, CreateEmailCommandResponse> {
    @Override
    public Email convert(CreateEmailCommandRequest source) {
        return new Email(
                source.getEmail()
        );
    }

    @Override
    public CreateEmailCommandResponse reverse(Email destination) {
        return CreateEmailCommandResponse.builder()
                .emailId(destination.getId())
                .email(destination.getEmail())
                .build();
    }
}

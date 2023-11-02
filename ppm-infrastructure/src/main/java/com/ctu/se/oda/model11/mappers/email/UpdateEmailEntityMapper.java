package com.ctu.se.oda.model11.mappers.email;

import com.ctu.se.oda.model11.entities.Email;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.email.UpdateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.email.UpdateEmailCommandResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UpdateEmailEntityMapper implements IInfrastructureMapper<UpdateEmailCommandRequest, Email, UpdateEmailCommandResponse> {
    @Override
    public Email convert(UpdateEmailCommandRequest source) {
        return new Email(
                source.getEmailId(),
                source.getEmail()
        );
    }

    @Override
    public UpdateEmailCommandResponse reverse(Email destination) {
        return UpdateEmailCommandResponse.builder()
                .emailId(destination.getId())
                .email(destination.getEmail())
                .build();
    }
}

package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.daos.IEmailService;
import com.ctu.se.oda.model11.interfaces.IEmailApplication;
import com.ctu.se.oda.model11.models.commands.requests.email.CreateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.email.UpdateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.email.CreateEmailCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.email.UpdateEmailCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.email.RetrieveEmailQueryResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@NoArgsConstructor
public class EmailApplication implements IEmailApplication {

    @Autowired
    private IEmailService emailService;

    @Override
    public CreateEmailCommandResponse createEmail(CreateEmailCommandRequest createEmailCommandRequest) {
        return emailService.createEmail(createEmailCommandRequest);
    }

    @Override
    public UpdateEmailCommandResponse updateEmail(UpdateEmailCommandRequest updateEmailCommandRequest) {
        return emailService.updateEmail(updateEmailCommandRequest);
    }

    @Override
    public List<RetrieveEmailQueryResponse> listEmail() {
        return emailService.listEmail();
    }

    @Override
    public RetrieveEmailQueryResponse detailEmail(UUID emailId) {
        return emailService.detailEmail(emailId);
    }

    @Override
    public void deleteEmail(UUID emailId) {
        emailService.deleteEmail(emailId);
    }
}

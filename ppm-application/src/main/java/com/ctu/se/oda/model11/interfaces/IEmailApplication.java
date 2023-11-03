package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.email.CreateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.email.UpdateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.email.CreateEmailCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.email.UpdateEmailCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.email.RetrieveEmailQueryResponse;

import java.util.List;
import java.util.UUID;

public interface IEmailApplication {

    CreateEmailCommandResponse createEmail(CreateEmailCommandRequest createEmailCommandRequest);

    UpdateEmailCommandResponse updateEmail(UpdateEmailCommandRequest updateEmailCommandRequest);

    List<RetrieveEmailQueryResponse> listEmail();

    RetrieveEmailQueryResponse detailEmail(UUID emailId);

    void deleteEmail(UUID emailId);
}

package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.models.commands.requests.email.CreateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.email.UpdateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.email.CreateEmailCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.email.UpdateEmailCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.email.RetrieveEmailQueryResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface IEmailService {
    CreateEmailCommandResponse createEmail(@Valid CreateEmailCommandRequest createEmailCommandRequest);
    UpdateEmailCommandResponse updateEmail(@Valid UpdateEmailCommandRequest updateEmailCommandRequest);
    List<RetrieveEmailQueryResponse> listEmail();
    RetrieveEmailQueryResponse detailEmail(UUID emailId);
    void deteleEmail(UUID emailId);
}

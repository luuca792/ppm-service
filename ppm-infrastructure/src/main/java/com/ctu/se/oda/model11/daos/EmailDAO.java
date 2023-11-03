package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Email;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.email.CreateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.email.UpdateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.email.CreateEmailCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.email.UpdateEmailCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.email.RetrieveEmailQueryResponse;
import com.ctu.se.oda.model11.repositories.IEmailRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Validated
public class EmailDAO implements IEmailService {

    @Autowired
    private IEmailRepository emailRepository;

    @Autowired
    private IInfrastructureMapper<CreateEmailCommandRequest, Email, CreateEmailCommandResponse> createEmailEntityMapper;

    @Autowired
    private IInfrastructureMapper<UpdateEmailCommandRequest, Email, UpdateEmailCommandResponse> updateEmailEntityMapper;

    @Override
    public CreateEmailCommandResponse createEmail(@Valid CreateEmailCommandRequest createEmailCommandRequest) {
        return createEmailEntityMapper.reverse(
                emailRepository.save(createEmailEntityMapper.convert(createEmailCommandRequest))
        );
    }

    @Override
    public UpdateEmailCommandResponse updateEmail(@Valid UpdateEmailCommandRequest updateEmailCommandRequest) {
        var retrievedEmail = emailRepository.findById(updateEmailCommandRequest.getEmailId());
        if (retrievedEmail.isEmpty()) {
            throw new InternalServerErrorException(CustomErrorMessage.EMAIL_ID_DO_NOT_EXIST);
        }
        return updateEmailEntityMapper.reverse(
                emailRepository.save(updateEmailEntityMapper.convert(updateEmailCommandRequest))
        );
    }

    @Override
    public List<RetrieveEmailQueryResponse> listEmail() {
        return emailRepository.findAll().stream()
                .map(email -> RetrieveEmailQueryResponse.builder()
                        .emailId(email.getId())
                        .emailAddress(email.getEmailAddress())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public RetrieveEmailQueryResponse detailEmail(UUID emailId) {
        var retrievedEmail = emailRepository.findById(emailId).get();
        if (Objects.isNull(retrievedEmail)) {
            throw new InternalServerErrorException(CustomErrorMessage.EMAIL_ID_DO_NOT_EXIST);
        }

        return RetrieveEmailQueryResponse.builder()
                .emailId(retrievedEmail.getId())
                .emailAddress(retrievedEmail.getEmailAddress())
                .build();
    }

    @Override
    public void deleteEmail(UUID emailId) {
        var retrievedEmail = emailRepository.findById(emailId).get();
        if (Objects.isNull(retrievedEmail)) {
            throw new InternalServerErrorException(CustomErrorMessage.EMAIL_ID_DO_NOT_EXIST);
        }
        emailRepository.deleteById(emailId);
    }
}

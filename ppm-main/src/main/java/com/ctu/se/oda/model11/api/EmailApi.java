package com.ctu.se.oda.model11.api;

import com.ctu.se.oda.model11.interfaces.IEmailApplication;
import com.ctu.se.oda.model11.mappers.IMainMapper;
import com.ctu.se.oda.model11.models.commands.requests.email.CreateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.email.UpdateEmailCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.email.CreateEmailCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.email.UpdateEmailCommandResponse;
import com.ctu.se.oda.model11.models.email.CreateEmailRequest;
import com.ctu.se.oda.model11.models.email.UpdateEmailRequest;
import com.ctu.se.oda.model11.models.queries.responses.email.RetrieveEmailQueryResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/emails")
public class EmailApi {

    @Autowired
    private IMainMapper<CreateEmailRequest, CreateEmailCommandRequest> createEmailMapper;

    @Autowired
    private IMainMapper<UpdateEmailRequest, UpdateEmailCommandRequest> updateEmailMapper;

    @Autowired
    private IEmailApplication emailApplication;

    @PostMapping()
    public ResponseEntity<CreateEmailCommandResponse> createEMail(@RequestBody @JsonProperty CreateEmailRequest createEmailRequest) {
        return new ResponseEntity<>(
                emailApplication.createEmail(createEmailMapper.convert(createEmailRequest)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{emailId}")
    public ResponseEntity<UpdateEmailCommandResponse> updateEmail(@RequestBody UpdateEmailRequest updateEmailRequest,
                                                                  @PathVariable String emailId) {
        updateEmailRequest.setEmailId(emailId);
        return new ResponseEntity<>(
                emailApplication.updateEmail(updateEmailMapper.convert(updateEmailRequest)),
                HttpStatus.OK
        );
    }

    @GetMapping()
    public ResponseEntity<List<RetrieveEmailQueryResponse>> listEmail() {
        return new ResponseEntity<>(
                emailApplication.listEmail(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<RetrieveEmailQueryResponse> detailEmail(@PathVariable String emailId) {
        return new ResponseEntity<>(
                emailApplication.detailEmail(UUID.fromString(emailId)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{emailId}")
    public ResponseEntity<?> deleteEmail(@PathVariable String emailId) {
        emailApplication.detailEmail(UUID.fromString(emailId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

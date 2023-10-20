package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.commands.requests.resource.CreateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.resource.UpdateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.resource.CreateResourceCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.resource.UpdateResourceCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.resource.RetrieveResourceQueryResponse;

import java.util.List;

public interface IResourceApplication {
    CreateResourceCommandResponse createResource(CreateResourceCommandRequest createResourceCommandRequest);
    UpdateResourceCommandResponse updateResource(UpdateResourceCommandRequest updateResourceCommandRequest, Long resourceId);
    List<RetrieveResourceQueryResponse> listResource();
    RetrieveResourceQueryResponse detailResource(Long resourceId);
    void deleteResource(Long resourceId);
    void deleteAllResource();
}

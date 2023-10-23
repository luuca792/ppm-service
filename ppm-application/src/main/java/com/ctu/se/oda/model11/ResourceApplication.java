package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.daos.IResourceService;
import com.ctu.se.oda.model11.interfaces.IResourceApplication;
import com.ctu.se.oda.model11.models.commands.requests.resource.CreateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.resource.UpdateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.resource.CreateResourceCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.resource.UpdateResourceCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.resource.RetrieveResourceQueryResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class ResourceApplication implements IResourceApplication {
    @Autowired
    private IResourceService resourceService;
    @Override
    public CreateResourceCommandResponse createResource(CreateResourceCommandRequest createResourceCommandRequest) {
        return this.resourceService.createResource(createResourceCommandRequest);
    }

    @Override
    public UpdateResourceCommandResponse updateResource(UpdateResourceCommandRequest updateResourceCommandRequest, Long resourceId) {
        return this.resourceService.updateResource(updateResourceCommandRequest, resourceId);
    }

    @Override
    public List<RetrieveResourceQueryResponse> listResource() {
        return this.resourceService.listResource();
    }

    @Override
    public RetrieveResourceQueryResponse detailResource(Long resourceId) {
        return this.resourceService.detailResource(resourceId);
    }

    @Override
    public void deleteResource(Long resourceId) {
        this.resourceService.deleteResource(resourceId);
    }

    @Override
    public void deleteAllResource() {
        this.resourceService.deleteAllResource();
    }
}

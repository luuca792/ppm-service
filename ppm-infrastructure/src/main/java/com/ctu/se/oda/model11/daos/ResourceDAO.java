package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Resource;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.mappers.IInfrastructureMapper;
import com.ctu.se.oda.model11.models.commands.requests.resource.CreateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.requests.resource.UpdateResourceCommandRequest;
import com.ctu.se.oda.model11.models.commands.responses.resource.CreateResourceCommandResponse;
import com.ctu.se.oda.model11.models.commands.responses.resource.UpdateResourceCommandResponse;
import com.ctu.se.oda.model11.models.queries.responses.resource.RetrieveResourceQueryResponse;
import com.ctu.se.oda.model11.repositories.IResourceRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Validated
public class ResourceDAO implements IResourceService{
    @Autowired
    private IResourceRepository resourceRepository;
    @Autowired
    private IInfrastructureMapper<CreateResourceCommandRequest, Resource, CreateResourceCommandResponse> createResourceEntityMapper;
    @Autowired
    private IInfrastructureMapper<UpdateResourceCommandRequest, Resource, UpdateResourceCommandResponse> updateResourceEntityMapper;

    @Override
    public CreateResourceCommandResponse createResource(@Valid CreateResourceCommandRequest createResourceCommandRequest) {
        return this.createResourceEntityMapper.reverse(
                this.resourceRepository.save(this.createResourceEntityMapper.convert(createResourceCommandRequest))
        );
    }
    @Override
    public UpdateResourceCommandResponse updateResource(@Valid UpdateResourceCommandRequest updateResourceCommandRequest, Long resourceId) {
        var retrievedResourceOptional = this.resourceRepository.findById(resourceId);
        if(retrievedResourceOptional.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        var retrievedResource = retrievedResourceOptional.get().getId();
        updateResourceCommandRequest.setResourceId(retrievedResource);
        return this.updateResourceEntityMapper.reverse(
                this.resourceRepository.save(this.updateResourceEntityMapper.convert(updateResourceCommandRequest))
        );
    }
    @Override
    public List<RetrieveResourceQueryResponse> listResource() {
        return this.resourceRepository.findAll().stream()
                .map(resource -> RetrieveResourceQueryResponse.builder().resourceId(resource.getId()).resourceName(resource.getName()).taskId(resource.getTaskId()).build())
                .collect(Collectors.toList());
    }
    @Override
    public RetrieveResourceQueryResponse detailResource(Long resourceId) {
        var retrievedResourceOptional = this.resourceRepository.findById(resourceId);
        if(retrievedResourceOptional.isEmpty()) {
            throw new IllegalArgumentException(CustomErrorMessage.NOT_FOUND_BY_ID);
        }
        var retrievedResource = retrievedResourceOptional.get();
        return RetrieveResourceQueryResponse.builder()
                .resourceId(retrievedResource.getId())
                .resourceName(retrievedResource.getName())
                .taskId(retrievedResource.getTaskId())
                .build();
    }

    @Override
    public void deleteResource(Long resourceId) {
        this.resourceRepository.deleteById(resourceId);
    }

    @Override
    public void deleteAllResource() {
        this.resourceRepository.deleteAll();
    }
}

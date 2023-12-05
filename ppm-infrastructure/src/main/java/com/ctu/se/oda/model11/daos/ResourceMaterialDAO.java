package com.ctu.se.oda.model11.daos;

import com.ctu.se.oda.model11.entities.Resource;
import com.ctu.se.oda.model11.entities.ResourceMaterial;
import com.ctu.se.oda.model11.errors.exceptions.InternalServerErrorException;
import com.ctu.se.oda.model11.errors.messages.CustomErrorMessage;
import com.ctu.se.oda.model11.models.dtos.ResourceMaterialDTO;
import com.ctu.se.oda.model11.repositories.IResourceMaterialRepository;
import com.ctu.se.oda.model11.repositories.IResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class ResourceMaterialDAO implements IResourceMaterialService{

    @Autowired
    private IResourceMaterialRepository resourceMaterialRepository;

    @Autowired
    private IResourceRepository resourceRepository;

    @Override
    public List<ResourceMaterialDTO> getAllByResourceId(UUID resourceId) {
        Resource resource = resourceRepository.findById(resourceId).orElseThrow(() -> new InternalServerErrorException(CustomErrorMessage.RESOURCE_NOT_FOUND));
        List<ResourceMaterial> resources = resourceMaterialRepository.findByResource(resource);
        if (Objects.isNull(resources)) {
            throw new InternalServerErrorException(CustomErrorMessage.RESOURCE_MATERIAL_RECORD_NOT_FOUND);
        }
        return resources.stream().map(resourceMaterial -> ResourceMaterialDTO.builder()
                .id(resourceMaterial.getId())
                .resourceId(resourceMaterial.getResource().getId())
                .materialId(resourceMaterial.getMaterial().getId())
                .amount(resourceMaterial.getAmount())
                .build()).collect(Collectors.toList());
    }
}

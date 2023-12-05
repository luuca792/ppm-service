package com.ctu.se.oda.model11;

import com.ctu.se.oda.model11.daos.IResourceMaterialService;
import com.ctu.se.oda.model11.interfaces.IResourceMaterialApplication;
import com.ctu.se.oda.model11.models.dtos.ResourceMaterialDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class ResourceMaterialApplication implements IResourceMaterialApplication {

    @Autowired
    private IResourceMaterialService resourceMaterialService;

    @Override
    public List<ResourceMaterialDTO> getAllByResourceId(UUID resourceId) {
        return resourceMaterialService.getAllByResourceId(resourceId);
    }
}

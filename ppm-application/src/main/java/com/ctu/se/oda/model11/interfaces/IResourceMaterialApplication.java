package com.ctu.se.oda.model11.interfaces;

import com.ctu.se.oda.model11.models.dtos.ResourceMaterialDTO;

import java.util.List;
import java.util.UUID;

public interface IResourceMaterialApplication {
    List<ResourceMaterialDTO> getAllByResourceId(UUID resourceId);
}

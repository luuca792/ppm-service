package com.ctu.se.oda.model11.mappers;

import com.ctu.se.oda.model11.entities.IEntity;
import com.ctu.se.oda.model11.models.IDomainModel;

public interface IInfrastructureMapper {
    IEntity mapping(IDomainModel request);
    IDomainModel reverse(IEntity object);
}

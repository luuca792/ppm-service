package com.ctu.se.oda.model11.mappers;

import com.ctu.se.oda.model11.models.IDomainModel;
import com.ctu.se.oda.model11.models.IModel;

public interface IMainMapper {
    IDomainModel convert(IModel request);
}

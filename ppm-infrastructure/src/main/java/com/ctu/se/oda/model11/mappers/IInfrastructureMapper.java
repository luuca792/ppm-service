package com.ctu.se.oda.model11.mappers;

public interface IInfrastructureMapper<A,E> {
    /**
     *  A - Request
     *  E - Entity
     * */
    E convert(A source);
}

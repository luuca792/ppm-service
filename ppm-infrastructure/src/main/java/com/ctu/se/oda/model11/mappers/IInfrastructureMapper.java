package com.ctu.se.oda.model11.mappers;

public interface IInfrastructureMapper<A,E,B> {
    /**
     *  A - Request
     *  B - Response
     *  E - Entity
     * */
    E convert(A source);
    B reverse(E destination);
}

package com.ctu.se.oda.model11.mappers;

public interface IMainMapper<S,D> {

    D convert(S source);

    S reverse(D destination);
}

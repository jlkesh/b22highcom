package dev.jlkeesh.service;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;

public interface GenericService<D, CD, UD, ID extends Serializable> {
    String create(@NotNull CD dto);

    boolean update(@NotNull UD dto);

    boolean delete(@NotNull ID id);

    D get(@NotNull ID id);

    List<D> getAll();
}

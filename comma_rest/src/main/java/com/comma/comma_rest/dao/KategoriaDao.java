package com.comma.comma_rest.dao;

import com.comma.comma_rest.entity.Kategoria;

import java.util.List;

public interface KategoriaDao {

    Kategoria findById(long id);

    List<Kategoria> findAll();

    Kategoria insert(Kategoria kategoria);
}

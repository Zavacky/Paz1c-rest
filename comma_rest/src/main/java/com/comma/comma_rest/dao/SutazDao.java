package com.comma.comma_rest.dao;

import com.comma.comma_rest.entity.Sutaz;

import java.util.List;

public interface SutazDao {

    Sutaz findById(int id);

    List<Sutaz> findAll();

    Sutaz insert(Sutaz sutaz);

    void update(Sutaz sutaz);

    boolean delete(Sutaz sutaz);
}

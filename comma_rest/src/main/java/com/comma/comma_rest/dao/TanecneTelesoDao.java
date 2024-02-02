package com.comma.comma_rest.dao;

import com.comma.comma_rest.entity.TanecneTeleso;

import java.util.List;

public interface TanecneTelesoDao {

    TanecneTeleso insert(TanecneTeleso tanecneTeleso);
    TanecneTeleso findById(long id);
    List<TanecneTeleso> findAll();
    List<TanecneTeleso> findAllBySutazId(int sutazId);
    List<TanecneTeleso> findAllBySutazIdKategoriaId(int sutazId, Long kategoriaId);
    TanecneTeleso update(TanecneTeleso tanecneTeleso);
    boolean delete(TanecneTeleso tanecneTeleso);
}

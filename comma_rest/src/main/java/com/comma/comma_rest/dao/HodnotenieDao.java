package com.comma.comma_rest.dao;

import com.comma.comma_rest.entity.Hodnotenie;

import java.util.List;

public interface HodnotenieDao {

    List<Hodnotenie> getAll();

    Hodnotenie getById(long id);

    List<Hodnotenie> getByTelesoId(long tanecneTelesoId);

    Hodnotenie save(Hodnotenie hodnotenie);

    Hodnotenie getByPorotcaIdAndTelesoId(long porotcaId, long tanecneTelesoId);

    void delete(long id);
    void deleteByTanecneTelesoId(long telesoId);
}

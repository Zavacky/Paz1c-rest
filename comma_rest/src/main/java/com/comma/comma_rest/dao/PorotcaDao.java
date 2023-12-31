package com.comma.comma_rest.dao;

import com.comma.comma_rest.entity.Porotca;

import java.util.List;

public interface PorotcaDao {
    Porotca findById(long id);

    Porotca insert(Porotca porotca);

    void pridajPorotcuDoSutaze(Long porotcaId, int sutazId);

    List<Porotca> getPorotcoviaPreSutaz(int idSutaze);

    void vymazPorotcuZoSutaze(Long porotcaId, int sutazId);

    void update(Porotca porotca);

    boolean delete(Porotca porotca);

    boolean isPasswordCorrect(String hashovane, String pouzivatelMeno);

    boolean existingUser(String meno);

    boolean isAdmin(String pouzivatelHeslo, String pouzivatelMeno);

    String getSalt(String uzivatelskeMeno);
}

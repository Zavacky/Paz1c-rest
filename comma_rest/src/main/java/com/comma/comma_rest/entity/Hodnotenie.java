package com.comma.comma_rest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Hodnotenie {
    private Long id;
    private int hodnotenie;
    private long porotcaId;
    private long tanecneTelesoId;

    public Hodnotenie(int hodnotenie, long porotcaId, long tanecneTelesoId) {
        this.hodnotenie = hodnotenie;
        this.porotcaId = porotcaId;
        this.tanecneTelesoId = tanecneTelesoId;
    }

    public Hodnotenie(long id, int hodnotenie, long porotcaId, long tanecneTelesoId) {
        this.id = id;
        this.hodnotenie = hodnotenie;
        this.porotcaId = porotcaId;
        this.tanecneTelesoId = tanecneTelesoId;
    }
}

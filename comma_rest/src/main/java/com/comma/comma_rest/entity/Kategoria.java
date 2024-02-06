package com.comma.comma_rest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Kategoria {
    private Long id;
    private String styl;
    private String vekovaSkupina;
    private String velkostnaSkupina;

    public Kategoria(Long id, String styl, String vekovaSkupina, String velkostnaSkupina) {
        this.id = id;
        this.styl = styl;
        this.vekovaSkupina = vekovaSkupina;
        this.velkostnaSkupina = velkostnaSkupina;
    }
}
package entity;

import lombok.Data;

@Data
public class Kategoria {
    private Long id;
    private String styl;
    private String vekovaSkupina;
    private String velkostnaSkupina;

    public Kategoria() {
    }

    public Kategoria(Long id, String styl, String vekovaSkupina, String velkostnaSkupina) {
        this.id = id;
        this.styl = styl;
        this.vekovaSkupina = vekovaSkupina;
        this.velkostnaSkupina = velkostnaSkupina;
    }
}
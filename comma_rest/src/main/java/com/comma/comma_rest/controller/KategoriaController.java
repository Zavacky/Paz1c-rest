package com.comma.comma_rest.controller;

import com.comma.comma_rest.dao.KategoriaDao;
import com.comma.comma_rest.entity.Kategoria;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KategoriaController {

    private final KategoriaDao kategoriaDao;

    public KategoriaController(KategoriaDao kategoriaDao) {
        this.kategoriaDao = kategoriaDao;
    }

    @GetMapping("/kategoria/all")
    public List<Kategoria> getAll() {
        return kategoriaDao.findAll();
    }

    @PostMapping("/kategoria/insert")
    public Kategoria insert(@RequestBody Kategoria kategoria) {
        return kategoriaDao.insert(kategoria);
    }

    @GetMapping("/kategoria/getKategoria/{id}")
    public Kategoria getKategoria(@PathVariable long id) {
        return kategoriaDao.findById(id);
    }

}
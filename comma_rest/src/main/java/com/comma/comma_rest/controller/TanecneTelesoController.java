package com.comma.comma_rest.controller;

import com.comma.comma_rest.dao.TanecneTelesoDao;
import com.comma.comma_rest.entity.TanecneTeleso;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TanecneTelesoController {
    private final TanecneTelesoDao tanecneTelesoDao;

    public TanecneTelesoController(TanecneTelesoDao tanecneTelesoDao) {
        this.tanecneTelesoDao = tanecneTelesoDao;
    }

    @PostMapping("/tanecneTeleso/new")
    public TanecneTeleso insert(TanecneTeleso tanecneTeleso) {
        return tanecneTelesoDao.insert(tanecneTeleso);
    }

    @GetMapping("/tanecneTeleso/{id}")
    public TanecneTeleso findById(@PathVariable long id) {
        return tanecneTelesoDao.findById(id);
    }

    @GetMapping("/tanecneTeleso/getAll")
    public List<TanecneTeleso> findAll() {
        return tanecneTelesoDao.findAll();
    }

    @GetMapping("/tanecneTeleso/getAllBySutazId/{id}")
    public List<TanecneTeleso> findAllBySutazId(@PathVariable long sutazId) {
        return null;
    }

    @GetMapping("/tanecneTeleso/getAllBySutazIdKategoriaId/{id}/{kategoriaId}")
    public List<TanecneTeleso> findAllBySutazIdKategoriaId(@PathVariable long sutazId, @PathVariable Long kategoriaId) {
        return tanecneTelesoDao.findAllBySutazIdKategoriaId(sutazId, kategoriaId);
    }

    @PutMapping("/tanecneTeleso/update")
    public TanecneTeleso update(@RequestBody TanecneTeleso tanecneTeleso) {
        return tanecneTelesoDao.update(tanecneTeleso);
    }

    @DeleteMapping("/tanecneTeleso/delete")
    public boolean delete(@RequestBody TanecneTeleso tanecneTeleso) {
        return tanecneTelesoDao.delete(tanecneTeleso);
    }
}

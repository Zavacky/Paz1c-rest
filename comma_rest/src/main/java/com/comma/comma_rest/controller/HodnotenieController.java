package com.comma.comma_rest.controller;

import com.comma.comma_rest.dao.HodnotenieDao;
import com.comma.comma_rest.entity.Hodnotenie;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HodnotenieController {

    private final HodnotenieDao hodnotenieDao;

    public HodnotenieController(HodnotenieDao hodnotenieDao) {
        this.hodnotenieDao = hodnotenieDao;
    }

    @GetMapping("/hodnotenie/all")
    public List<Hodnotenie> getAll() {
        return hodnotenieDao.getAll();
    }

    @GetMapping("/hodnotenie/getById/{id}")
    public Hodnotenie getById(@PathVariable long id) {
        return hodnotenieDao.getById(id);
    }

    @GetMapping("/hodnotenie/getByTelesoId/{id}")
    public List<Hodnotenie> getByTelesoId(@PathVariable long id) {
        return hodnotenieDao.getByTelesoId(id);
    }

    @PostMapping("/hodnotenie/save")
    public Hodnotenie save(@RequestBody Hodnotenie hodnotenie){
        return hodnotenieDao.save(hodnotenie);
    }

    @GetMapping("/hodnotenie/getByPorotcaIdAndTelesoId/{porotcaId}/{telesoId}")
    public Hodnotenie getByPorotcaIdAndTelesoId(@PathVariable long porotcaId, @PathVariable long telesoId) {
        return hodnotenieDao.getByPorotcaIdAndTelesoId(porotcaId, telesoId);
    }

    @DeleteMapping("/hodnotenie/delete/{id}")
    public void delete(@PathVariable long id){
        hodnotenieDao.delete(id);
    }

    @DeleteMapping("/hodnotenie/deleteByTanecneTelesoId/{telesoId}")
    public void deleteByTanecneTelesoId(@PathVariable long telesoId){
        hodnotenieDao.deleteByTanecneTelesoId(telesoId);
    }



}

package com.comma.comma_rest.controller;

import com.comma.comma_rest.dao.PorotcaDao;
import com.comma.comma_rest.entity.Porotca;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PorotcaController {
    private final PorotcaDao porotcaDao;

    public PorotcaController(PorotcaDao porotcaDao) {
        this.porotcaDao = porotcaDao;
    }

    @GetMapping("/porotca/{id}")
    public Porotca findById(@PathVariable long id) {
        return porotcaDao.findById(id);
    }

    @PostMapping("/porotca/new")
    public Porotca insert(@RequestBody Porotca porotca) {
        return porotcaDao.insert(porotca);
    }

    @PutMapping("/porotca/pridajDoSutaze/{porotcaId}/{sutazId}")
    public void pridajPorotcuDoSutaze(@PathVariable long porotcaId, @PathVariable int sutazId) {
        porotcaDao.pridajPorotcuDoSutaze(porotcaId, sutazId);
    }

    @GetMapping("/porotca/getPorotcoviaPreSutaz/{id}")
    public List<Porotca> getPorotcoviaPreSutaz(@PathVariable int id) {
        return porotcaDao.getPorotcoviaPreSutaz(id);
    }

    @DeleteMapping("/porotca/vymazPorotcuZoSutaze/{porotcaId}/{sutazId}")
    public void vymazPorotcuZoSutaze(@PathVariable long porotcaId, @PathVariable int sutazId) {
        porotcaDao.vymazPorotcuZoSutaze(porotcaId, sutazId);
    }

    @PutMapping("/porotca/update")
    public void update(@RequestBody Porotca porotca) {
        porotcaDao.update(porotca);
    }

    @DeleteMapping("/porotca/delete")
    public void delete(@RequestBody Porotca porotca) {
        porotcaDao.delete(porotca);
    }

    @GetMapping("/porotca/isPasswordCorrect/{uzivatelskeMeno}/{heslo}")
    public boolean isPasswordCorrect(@PathVariable String uzivatelskeMeno, @PathVariable String heslo) {
        return porotcaDao.isPasswordCorrect(heslo, uzivatelskeMeno);
    }

    @GetMapping("/porotca/existingUser/{uzivatelskeMeno}")
    public boolean existingUser(@PathVariable String uzivatelskeMeno) {
        return porotcaDao.existingUser(uzivatelskeMeno);
    }

    @GetMapping("/porotca/isAdmin/{uzivatelskeMeno}/{heslo}")
    public boolean isAdmin(@PathVariable String uzivatelskeMeno, @PathVariable String heslo) {
        return porotcaDao.isAdmin(heslo, uzivatelskeMeno);
    }

    @GetMapping("/porotca/getSalt/{uzivatelskeMeno}")
    public String getSalt(@PathVariable String uzivatelskeMeno) {
        return porotcaDao.getSalt(uzivatelskeMeno);
    }
}

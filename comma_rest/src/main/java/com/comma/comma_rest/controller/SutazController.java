package com.comma.comma_rest.controller;
import com.comma.comma_rest.dao.SutazDao;
import com.comma.comma_rest.entity.Sutaz;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SutazController {
    private final SutazDao sutazDao;

    public SutazController(SutazDao sutazDao) {
        this.sutazDao = sutazDao;
    }

    @GetMapping("/sutaz/{id}")
    public Sutaz findById(@PathVariable long id) {
        return sutazDao.findById(id);
    }

    @GetMapping("/sutaz/all")
    public List<Sutaz> findAll() {
        return sutazDao.findAll();
    }

    @PostMapping("/sutaz/new")
    public Sutaz insert(@RequestBody Sutaz sutaz) {
        return sutazDao.insert(sutaz);
    }

    @PutMapping("/sutaz/update")
    public void update(@RequestBody Sutaz sutaz) {
        sutazDao.update(sutaz);
    }

    @DeleteMapping("/sutaz/delete")
    public boolean delete(@RequestBody Sutaz sutaz) {
        return sutazDao.delete(sutaz);
    }
}

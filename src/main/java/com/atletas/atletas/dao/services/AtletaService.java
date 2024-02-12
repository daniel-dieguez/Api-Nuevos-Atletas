package com.atletas.atletas.dao.services;

import com.atletas.atletas.dao.IAtletasDao;
import com.atletas.atletas.models.model.Atletas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtletaService implements IAtletasServicesImpl{

    @Autowired
    private IAtletasDao iAtletasDao;



    @Override
    public List<Atletas> findAll() {
        return this.iAtletasDao.findAll();
    }

    @Override
    public Page<Atletas> findAll(Pageable pageable) {
        return this.iAtletasDao.findAll(pageable);
    }

    @Override
    public Atletas findById(String id_atleta) {
        return this.iAtletasDao.findById(id_atleta).orElse(null);
    }

    @Override
    public Atletas save(Atletas atletas) {
        return this.iAtletasDao.save(atletas);
    }

    @Override
    public void delete(Atletas atletas) {
        this.iAtletasDao.delete(atletas);

    }
}

package com.atletas.atletas.dao.services;

import com.atletas.atletas.models.model.Atletas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAtletasServicesImpl {

    public List<Atletas> findAll();
    public Page<Atletas> findAll(Pageable pageable);
    public Atletas findById(String id_atleta);
    public Atletas save(Atletas atletas);
    public void delete(Atletas atletas);

}

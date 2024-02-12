package com.atletas.atletas.dao;

import com.atletas.atletas.models.entities.AtletasDTO;
import com.atletas.atletas.models.model.Atletas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAtletasDao extends JpaRepository<Atletas, Object> {
}

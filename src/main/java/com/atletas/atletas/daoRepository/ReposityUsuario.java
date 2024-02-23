package com.atletas.atletas.daoRepository;

import com.atletas.atletas.models.model.Atletas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReposityUsuario extends JpaRepository<Atletas, Integer> {


    Optional<Atletas> findOneByEmail(String email);
}

package com.atletas.atletas.autentication;


import com.atletas.atletas.dao.IAtletasDao;
import com.atletas.atletas.daoRepository.ReposityUsuario;
import com.atletas.atletas.models.model.Atletas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAtletasDao iAtletasDao; // viene del repositorio DAO

    @Autowired
    private ReposityUsuario reposityUsuario;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Atletas atletas = iAtletasDao.findOneByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("el usuario con emial " + email + "no existe"));

        return new UserDetailsImpl(atletas);

    }
}

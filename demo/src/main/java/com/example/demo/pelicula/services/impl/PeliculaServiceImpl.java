package com.example.demo.pelicula.services.impl;


import java.util.List;
import java.util.Optional;

import com.example.demo.pelicula.model.Pelicula;
import com.example.demo.pelicula.repository.PeliculaRepository;
import com.example.demo.pelicula.services.PeliculaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;

@Service
public class PeliculaServiceImpl implements PeliculaService {
    private static final Logger log = LoggerFactory.getLogger(PeliculaServiceImpl.class);
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getAllPelicula() {
        log.info("extrayendo datos");
        return peliculaRepository.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(int id) {
        return peliculaRepository.findById(id);
    }

    @Override
    @Transactional
    public Pelicula crear(Pelicula p) {
        // Ignoramos ID si viene seteado
        p.setId(0);
        return peliculaRepository.save(p);
    }

    @Override
    @Transactional
    public boolean eliminarPorId(int id) {
        if (!peliculaRepository.existsById(id)) return false;
        peliculaRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public long eliminarPorTitulo(String titulo) {
        return peliculaRepository.deleteByTitulo(titulo);
    }
}

package com.example.demo.pelicula.repository;

import com.example.demo.pelicula.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    long deleteByTitulo(String titulo);
}

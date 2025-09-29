package com.example.demo.pelicula.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.pelicula.model.Pelicula;
import org.springframework.stereotype.Service;


@Service
public interface PeliculaService {

    List<Pelicula> getAllPelicula();
    Optional<Pelicula> getPeliculaById(int id);
    Pelicula crear(Pelicula p);
    boolean eliminarPorId(int id);
    long eliminarPorTitulo(String titulo);

}

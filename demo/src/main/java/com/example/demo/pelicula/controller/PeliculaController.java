package com.example.demo.pelicula.controller;

import java.net.URI;
import java.util.List;

import com.example.demo.pelicula.model.Pelicula;
import com.example.demo.pelicula.services.PeliculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {
    private final PeliculaService service;
    public PeliculaController(PeliculaService service){ this.service = service; }

    @GetMapping
    public ResponseEntity<List<Pelicula>> todas() {
        return ResponseEntity.ok(service.getAllPelicula());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> porId(@PathVariable int id) {
        return service.getPeliculaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PeliculaRequest req) {
        Pelicula p = new Pelicula();
        p.setTitulo(req.getTitulo());
        p.setAnio(req.getAnio());
        p.setDirector(req.getDirector());
        p.setGenero(req.getGenero());
        p.setSinopsis(req.getSinopsis());

        Pelicula creado = service.crear(p);
        return ResponseEntity
                .created(URI.create("/peliculas/" + creado.getId()))
                .body(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable int id) {
        boolean ok = service.eliminarPorId(id);
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}

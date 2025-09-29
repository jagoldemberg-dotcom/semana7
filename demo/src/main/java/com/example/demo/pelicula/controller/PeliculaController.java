package com.example.demo.pelicula.controller;

import java.net.URI;
import java.util.List;

import com.example.demo.pelicula.model.Pelicula;
import com.example.demo.pelicula.services.PeliculaService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {
    private final PeliculaService service;
    public PeliculaController(PeliculaService service){ this.service = service; }

    private EntityModel<Pelicula> toModel(Pelicula p){
        return EntityModel.of(
                p,
                linkTo(methodOn(PeliculaController.class).porId(p.getId())).withSelfRel(),
                linkTo(methodOn(PeliculaController.class).todas()).withRel("all")
        );
    }



    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> todas() {
        var models = service.getAllPelicula().stream().map(this::toModel).toList();
        return CollectionModel.of(models,
                linkTo(methodOn(PeliculaController.class).todas()).withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pelicula>> porId(@PathVariable int id) {
        return service.getPeliculaById(id)
                .map(p -> ResponseEntity.ok(toModel(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Pelicula>> crear(@Valid @RequestBody PeliculaRequest req) {
        Pelicula p = new Pelicula();
        p.setTitulo(req.getTitulo());
        p.setAnio(req.getAnio());
        p.setDirector(req.getDirector());
        p.setGenero(req.getGenero());
        p.setSinopsis(req.getSinopsis());

        Pelicula creado = service.crear(p);
        var body = toModel(creado);
        return ResponseEntity
                .created(URI.create("/peliculas/" + creado.getId()))
                .body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPorId(@PathVariable int id) {
        return service.eliminarPorId(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}

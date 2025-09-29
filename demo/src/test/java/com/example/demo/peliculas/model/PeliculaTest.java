package com.example.demo.peliculas.model;

import com.example.demo.pelicula.model.Pelicula;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PeliculaModelTest {

    @Test
    void gettersSetters_ok() {
        Pelicula p = new Pelicula();
        p.setId(10);
        p.setTitulo("Duna");
        p.setAnio(2021);
        p.setDirector("Villeneuve");
        p.setGenero("Sci-Fi");
        p.setSinopsis("Arrakis...");

        assertThat(p.getId()).isEqualTo(10);
        assertThat(p.getTitulo()).isEqualTo("Duna");
        assertThat(p.getAnio()).isEqualTo(2021);
        assertThat(p.getDirector()).isEqualTo("Villeneuve");
        assertThat(p.getGenero()).isEqualTo("Sci-Fi");
        assertThat(p.getSinopsis()).isEqualTo("Arrakis...");
    }

    @Test
    void idsDiferentes_valoresDiferentes() {
        Pelicula a = new Pelicula(); a.setId(1);
        Pelicula b = new Pelicula(); b.setId(2);
        assertThat(a.getId()).isNotEqualTo(b.getId());
    }
}


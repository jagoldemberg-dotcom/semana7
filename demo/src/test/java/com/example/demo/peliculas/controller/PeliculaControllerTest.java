package com.example.demo.peliculas.controller;

import com.example.demo.pelicula.controller.PeliculaController;
import com.example.demo.pelicula.model.Pelicula;
import com.example.demo.pelicula.services.PeliculaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeliculaController.class)
class PeliculaControllerTest {

    @Autowired MockMvc mvc;
    @MockBean
    PeliculaService service;

    private Pelicula sample() {
        Pelicula p = new Pelicula();
        p.setId(1);
        p.setTitulo("Interestelar");
        p.setAnio(2014);
        p.setDirector("Nolan");
        p.setGenero("Sci-Fi");
        p.setSinopsis("Viaje espacial");
        return p;
    }

    @Test
    void listar_ok() throws Exception {
        when(service.getAllPelicula()).thenReturn(List.of(sample()));
        mvc.perform(get("/peliculas"))
                .andExpect(status().isOk());
    }

    @Test
    void crear_created() throws Exception {
        Pelicula creado = sample(); // con id
        when(service.crear(org.mockito.ArgumentMatchers.any(Pelicula.class)))
                .thenReturn(creado);

        String json = """
          {
            "titulo":"Interestelar",
            "anio":2014,
            "director":"Nolan",
            "genero":"Sci-Fi",
            "sinopsis":"Viaje espacial"
          }
        """;

        mvc.perform(post("/peliculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", org.hamcrest.Matchers.containsString("/peliculas/")));
    }
}
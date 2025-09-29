package com.example.demo.peliculas.services.impl;

import com.example.demo.pelicula.model.Pelicula;
import com.example.demo.pelicula.repository.PeliculaRepository;
import com.example.demo.pelicula.services.impl.PeliculaServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PeliculaServiceImplTest {

    @Mock
    PeliculaRepository repo;
    @InjectMocks
    PeliculaServiceImpl service;
    AutoCloseable mocks;

    @BeforeEach void init(){ mocks = MockitoAnnotations.openMocks(this); }
    @AfterEach void close() throws Exception { mocks.close(); }

    private Pelicula sample(){
        Pelicula p = new Pelicula();
        p.setId(1);
        p.setTitulo("Interestelar");
        return p;
    }

    @Test
    void getAllPelicula_devuelveLista() {
        when(repo.findAll()).thenReturn(List.of(sample()));
        assertThat(service.getAllPelicula()).hasSize(1);
    }

    @Test
    void eliminarPorId_trueCuandoExiste_falseCuandoNo() {
        when(repo.existsById(5)).thenReturn(true);
        assertThat(service.eliminarPorId(5)).isTrue();
        verify(repo).deleteById(5);

        when(repo.existsById(9)).thenReturn(false);
        assertThat(service.eliminarPorId(9)).isFalse();
        verify(repo, never()).deleteById(9);
    }
}
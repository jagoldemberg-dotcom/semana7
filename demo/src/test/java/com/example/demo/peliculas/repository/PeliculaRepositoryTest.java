package com.example.demo.peliculas.repository;

import com.example.demo.pelicula.model.Pelicula;
import com.example.demo.pelicula.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PeliculaRepositoryTest {

    @Test
    void deleteByTitulo_retornaConteoMockeado() {
        PeliculaRepository repo = mock(PeliculaRepository.class);
        when(repo.deleteByTitulo("Interestelar")).thenReturn(2L);

        long deleted = repo.deleteByTitulo("Interestelar");
        assertThat(deleted).isEqualTo(2L);
        verify(repo).deleteByTitulo("Interestelar");
    }

    @Test
    void save_capturaEntidad() {
        PeliculaRepository repo = mock(PeliculaRepository.class);
        when(repo.save(any(Pelicula.class))).thenAnswer(inv -> inv.getArgument(0));

        Pelicula p = new Pelicula();
        p.setTitulo("Matrix");
        p.setAnio(1999);
        repo.save(p);

        ArgumentCaptor<Pelicula> captor = ArgumentCaptor.forClass(Pelicula.class);
        verify(repo).save(captor.capture());
        assertThat(captor.getValue().getTitulo()).isEqualTo("Matrix");
        assertThat(captor.getValue().getAnio()).isEqualTo(1999);
    }
}


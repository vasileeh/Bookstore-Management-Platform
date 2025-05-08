package com.example.bookstore.service;

import com.example.bookstore.service.GenreService;
import com.example.bookstore.model.Genre;
import com.example.bookstore.repository.GenreRepository;
import org.h2.command.dml.Update;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenreServiceTest {
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    private Genre genre;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        genre=new Genre();
        genre.setId(7L);
        genre.setName("Test Genre");
        genre.setDescription("Test description");
        genre.setBooks(new ArrayList<>());
    }

    @Test
    void testCreateGenre(){
        when(genreRepository.save(genre)).thenReturn(genre);

        Genre saved=genreService.createGenre(genre);

        assertEquals("Test Genre",saved.getName());
        verify(genreRepository, times(1)).save(genre);
    }

    @Test
    void testGetAllGenres(){
        List<Genre> genres=List.of(genre);
        when(genreRepository.findAll()).thenReturn(genres);

        List<Genre> result=genreService.getAllGenres();

        assertEquals(1, result.size());
        verify(genreRepository, times(1)).findAll();
    }

    @Test
    void testGetGenreById_Found(){
        when(genreRepository.findById(7L)).thenReturn(Optional.of(genre));

        Genre result=genreService.getGenreById(7L);

        assertEquals("Test Genre",result.getName());
    }

    @Test
    void testGetGenreById_NotFound(){
        when(genreRepository.findById(6L)).thenReturn(Optional.empty());

        RuntimeException exception=assertThrows(RuntimeException.class,
                ()->genreService.getGenreById(6L));
        assertEquals("The genre was not found",exception.getMessage());
    }

    @Test
    void testGetGenreByName_Found(){
        when(genreRepository.findByName("Test Genre")).thenReturn(Optional.of(genre));

        Genre result=genreService.getGenreByName("Test Genre");

        assertEquals("Test Genre", result.getName());
    }
}

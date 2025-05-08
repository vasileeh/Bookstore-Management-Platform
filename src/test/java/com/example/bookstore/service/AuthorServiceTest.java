package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        author = new Author();
        author.setId(1L);
        author.setName("Test Author");
        author.setBiography("Test Biography");
        author.setBooks(new ArrayList<>());
    }

    @Test
    void testCreateAuthor() {
        when(authorRepository.save(author)).thenReturn(author);

        Author saved = authorService.createAuthor(author);

        assertEquals("Test Author", saved.getName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void testGetAllAuthors() {
        List<Author> authors = List.of(author);
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAllAuthors();

        assertEquals(1, result.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testGetAuthorById_Found() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author result = authorService.getAuthorById(1L);

            assertEquals("Test Author", result.getName());
    }

    @Test
    void testGetAuthorById_NotFound() {
        when(authorRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> authorService.getAuthorById(2L));
        assertEquals("The author was not found", exception.getMessage());
    }

    @Test
    void testGetAuthorByName_Found() {
        when(authorRepository.findByName("Test Author")).thenReturn(Optional.of(author));

        Author result = authorService.getAuthorByName("Test Author");

        assertEquals("Test Author", result.getName());
    }

    @Test
    void testUpdateAuthor() {
        Author updatedAuthor = new Author();
        updatedAuthor.setName("Test Autor2");
        updatedAuthor.setBiography("New biography");
        updatedAuthor.setBooks(new ArrayList<>());

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenAnswer(i -> i.getArgument(0));

        Author result = authorService.updateAuthor(1L, updatedAuthor);

        assertEquals("Test Autor2", result.getName());
        assertEquals("New biography", result.getBiography());
    }

    @Test
    void testDeleteAuthor() {
        doNothing().when(authorRepository).deleteById(1L);

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }
}

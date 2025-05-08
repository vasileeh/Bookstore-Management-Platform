package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Genre;
import com.example.bookstore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        Author author = Author.builder()
                .id(7L)
                .name("Test author")
                .biography("Some biography")
                .books(new ArrayList<>())
                .build();

        Genre genre = Genre.builder()
                .id(7L)
                .name("Fiction")
                .description("Fictional books")
                .books(new ArrayList<>())
                .build();

        book = Book.builder()
                .id(7L)
                .title("Test Book")
                .author(author)
                .genre(genre)
                .price(29.99)
                .stock(10)
                .genres(List.of(genre))
                .build();
    }

    @Test
    void createBook_shouldReturnSavedBook() {
        when(bookRepository.save(book)).thenReturn(book);

        Book saved = bookService.createBook(book);

        assertNotNull(saved);
        assertEquals("Test Book", saved.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void getBookById_existingId_shouldReturnBook() {
        when(bookRepository.findById(7L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(7L);

        assertNotNull(result);
        assertEquals(7L, result.getId());
        verify(bookRepository).findById(7L);
    }

    @Test
    void getBookById_nonExistingId_shouldThrowException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> bookService.getBookById(99L));
        assertEquals("The book was not found", ex.getMessage());
        verify(bookRepository).findById(99L);
    }

    @Test
    void deleteBook_shouldCallDeleteById() {
        bookService.deleteBook(7L);

        verify(bookRepository, times(1)).deleteById(7L);
    }

    @Test
    void getAllBooks_shouldReturnList() {
        List<Book> books = List.of(book);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
        verify(bookRepository).findAll();
    }

    @Test
    void updateBook_shouldUpdateFields() {
        when(bookRepository.findById(7L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book updatedData = Book.builder()
                .title("Updated Title")
                .author(book.getAuthor())
                .genre(book.getGenre())
                .price(49.99)
                .stock(5)
                .build();

        Book updated = bookService.updateBook(7L, updatedData);

        assertEquals("Updated Title", updated.getTitle());
        assertEquals(49.99, updated.getPrice());
        assertEquals(5, updated.getStock());
        verify(bookRepository).findById(7L);
        verify(bookRepository).save(any(Book.class));
    }
}

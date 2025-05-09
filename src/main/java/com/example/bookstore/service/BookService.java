package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()->new RuntimeException("The book was not found"));
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> filterBooks(String filter, String filterValue) {
        switch (filter) {
            case "genre":
                return bookRepository.findByGenreName(filterValue);
            case "title":
                return bookRepository.findByTitleContainingIgnoreCase(filterValue);
            default:
                return getAllBooks();
        }
    }

    public Book updateBook(Long id, Book updateBook){
        Book existing=getBookById(id);
        existing.setTitle(updateBook.getTitle());
        existing.setGenre(updateBook.getGenre());
        existing.setAuthor(updateBook.getAuthor());
        existing.setPrice(updateBook.getPrice());
        existing.setStock(updateBook.getStock());

        return bookRepository.save(existing);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}

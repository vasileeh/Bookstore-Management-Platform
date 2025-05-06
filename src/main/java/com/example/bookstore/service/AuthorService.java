package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(()->new RuntimeException("The author was not found"));
    }

    public Author getAuthorByName(String name){
        return authorRepository.findByName(name)
                .orElseThrow(()->new RuntimeException("The author was not found"));
    }

    public Author updateAuthor(Long id, Author updateAuthor){
        Author existing=getAuthorById(id);
        existing.setName(updateAuthor.getName());
        existing.setBooks(updateAuthor.getBooks());
        existing.setBiography(updateAuthor.getBiography());

        return authorRepository.save(existing);
    }

    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }
}

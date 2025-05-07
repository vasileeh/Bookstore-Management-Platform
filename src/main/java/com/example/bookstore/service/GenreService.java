package com.example.bookstore.service;

import com.example.bookstore.model.Genre;
import com.example.bookstore.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Genre createGenre(Genre genre){
        return genreRepository.save(genre);
    }

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id){
        return genreRepository.findById(id)
                .orElseThrow(()->new RuntimeException("The genre was not found"));
    }

    public Genre getGenreByName(String name){
        return genreRepository.findByName(name)
                .orElseThrow(()->new RuntimeException("The genre was not found"));
    }
}

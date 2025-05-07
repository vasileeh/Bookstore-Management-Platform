package com.example.bookstore.controller;
import com.example.bookstore.model.Genre;
import com.example.bookstore.repository.GenreRepository;
import com.example.bookstore.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<Genre> createGenre(@PathVariable Genre genre){
        return ResponseEntity.ok(genreService.createGenre(genre));
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres(){
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id){
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Genre> getGenreByName(@PathVariable String name){
        return ResponseEntity.ok(genreService.getGenreByName(name));
    }
}

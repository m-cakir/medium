package com.mcakir.bootiful.controllers;

import com.mcakir.bootiful.models.Movie;
import com.mcakir.bootiful.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieRestController {

    @Autowired
    IMovieService movieService;

    @GetMapping(value = "/")
    public ResponseEntity<List<Movie>> getMovies(){

        List<Movie> movies = movieService.findAll();

        return new ResponseEntity<>(movies, HttpStatus.FOUND);
    }
}

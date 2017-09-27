package com.mcakir.bootiful.services;

import com.mcakir.bootiful.models.Movie;

import java.util.List;

public interface IMovieService {

    List<Movie> findAll();

    Movie findOne(Long id);

    Movie newOne(Movie movie);

}

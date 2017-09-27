package com.mcakir.bootiful.controllers;

import com.mcakir.bootiful.models.Movie;
import com.mcakir.bootiful.repositories.MovieRepository;
import com.mcakir.bootiful.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    IMovieService movieService;

    @Autowired
    MovieRepository repository;

    @GetMapping(value = "/")
    public String movies(ModelMap modelMap, @RequestParam(value = "id", required = false) Long id){

        long start = System.currentTimeMillis();

        if(id != null){

            modelMap.addAttribute("movie", movieService.findOne(id));

        }

        modelMap.addAttribute("movies", movieService.findAll());

        modelMap.addAttribute("speed", (System.currentTimeMillis() - start) / 1000.0);

        return "movies";
    }

    @PostMapping(value = "/")
    public String createNewOne(@RequestParam(value = "name", required = true) String name,
                               @RequestParam(value = "director", required = true) String director){

        if(name != null && name.length() != 0
                && director != null && director.length() != 0){

            Movie movie = new Movie();
            movie.setName(name);
            movie.setDirector(director);

            movieService.newOne(movie);

        }

        return "redirect:/";
    }

    @PostMapping(value = "/movie/edit")
    public String editOne(@RequestParam(value = "id", required = true) Long id,
                          @RequestParam(value = "name", required = true) String name,
                          @RequestParam(value = "director", required = true) String director){

        if(id != null
                && name != null && name.length() != 0
                && director != null && director.length() != 0){

            Movie movie = movieService.findOne(id);

            if(movie != null){

                movie.setName(name);
                movie.setDirector(director);

                movieService.newOne(movie);
            }

        }

        return "redirect:/";
    }
}

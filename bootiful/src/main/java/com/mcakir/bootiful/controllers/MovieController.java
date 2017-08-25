package com.mcakir.bootiful.controllers;

import com.mcakir.bootiful.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {

    @Autowired
    IMovieService movieService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String movies(ModelMap modelMap){

        modelMap.addAttribute("movies", movieService.findAll());

        return "movies";
    }

    @RequestMapping(value = "/throw-500", method = RequestMethod.GET)
    public String test(){

        int x = 1000 / 0;

        return "movies";
    }
}

package com.example.testgraphdbproject.domain.movie.api;

import com.example.testgraphdbproject.domain.movie.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
}


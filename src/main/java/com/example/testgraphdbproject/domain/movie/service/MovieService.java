package com.example.testgraphdbproject.domain.movie.service;



import com.example.testgraphdbproject.domain.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}


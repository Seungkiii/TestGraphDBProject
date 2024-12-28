package com.example.testgraphdbproject.domain.movie.repository;

import com.example.testgraphdbproject.domain.movie.entity.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends Neo4jRepository<Movie, String> {
    @Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
    List<Movie> findSearchResults(@Param("title") String title);

    List<Movie> findALLByTitleLike(@Param("title") String title);

}
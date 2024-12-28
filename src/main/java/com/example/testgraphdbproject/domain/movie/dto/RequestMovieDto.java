package com.example.testgraphdbproject.domain.movie.dto;

import com.example.testgraphdbproject.domain.person.entity.Person;
import com.example.testgraphdbproject.domain.person.relationship.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestMovieDto {
    private String title;
    private String tagline;
    private final Set<Role> actors = new HashSet<>();
    private final Set<Role> directors = new HashSet<>();
    private Integer released;
    private Long votes;
}

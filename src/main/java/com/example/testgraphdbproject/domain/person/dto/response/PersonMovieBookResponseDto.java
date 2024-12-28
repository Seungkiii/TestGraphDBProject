package com.example.testgraphdbproject.domain.person.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonMovieBookResponseDto {
    private String personName;
    private String movieTitle;
    private String bookTitle;
}

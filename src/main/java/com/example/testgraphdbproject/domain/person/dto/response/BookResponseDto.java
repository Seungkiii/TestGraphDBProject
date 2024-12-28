package com.example.testgraphdbproject.domain.person.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private String title;
    private List<String> authors;
}


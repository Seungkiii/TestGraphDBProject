package com.example.testgraphdbproject.domain.person.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddReadBookRequestDto {
    private String title;
    private List<String> authors;
}

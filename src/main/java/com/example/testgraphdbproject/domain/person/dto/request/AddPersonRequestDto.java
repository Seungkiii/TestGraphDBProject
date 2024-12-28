package com.example.testgraphdbproject.domain.person.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddPersonRequestDto {
    private String name;
    private int age;
    private List<String> booksRead;  // 책 제목 목록
    private String movieTitle;      // 영화 제목
}


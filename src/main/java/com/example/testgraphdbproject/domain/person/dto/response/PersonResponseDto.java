package com.example.testgraphdbproject.domain.person.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDto {
    private String name;
    private int age;
    private String movieTitle;      // 참여한 영화 제목
    private String bookTitle;       // 읽은 책 제목
}

package com.example.testgraphdbproject.domain.book.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Getter
@Setter
@Node
public class Book {
    @Id
    private String title;
    private List<String> authors;  // List<String>로 수정
}


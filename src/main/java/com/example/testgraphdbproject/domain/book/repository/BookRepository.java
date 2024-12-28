package com.example.testgraphdbproject.domain.book.repository;

import com.example.testgraphdbproject.domain.book.entity.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BookRepository extends Neo4jRepository<Book, String> {
}


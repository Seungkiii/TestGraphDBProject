package com.example.testgraphdbproject.domain.person.entity;

import com.example.testgraphdbproject.domain.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Node
public class Person {
    @Id
    private String name;

    private int age;

    @Relationship(type = "IS_FRIENDS_WITH", direction = Relationship.Direction.OUTGOING)
    private Set<Person> friends = new HashSet<>();

    @Relationship(type = "HAS_READ", direction = Relationship.Direction.OUTGOING)
    private Set<Book> booksRead = new HashSet<>();

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}


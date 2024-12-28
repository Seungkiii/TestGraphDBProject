package com.example.testgraphdbproject.domain.movie.entity;

import com.example.testgraphdbproject.domain.book.entity.Book;
import com.example.testgraphdbproject.domain.person.entity.Person;
import com.example.testgraphdbproject.domain.person.relationship.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;
import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private String title;

    @Property
    private String tagline;

    @Relationship(type = "ACTED_IN", direction = INCOMING)
    private List<Role> actors;

    @Relationship(type = "DIRECTED", direction = INCOMING)
    private List<Role> directors;

    @Relationship(type = "ORIGINAL", direction = OUTGOING)
    private Book originalBook;

    @Property
    private Integer released;

    @Property
    private Long votes;
}


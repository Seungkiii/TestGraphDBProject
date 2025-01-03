package com.example.testgraphdbproject.domain.person.relationship;

import com.example.testgraphdbproject.domain.person.entity.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @TargetNode
    private final Person person;

    public Role(Person person) {
        this.person = person;
    }
}

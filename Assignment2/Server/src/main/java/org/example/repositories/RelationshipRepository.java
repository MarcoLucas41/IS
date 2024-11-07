package org.example.repositories;

import org.example.entities.Relationship;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationshipRepository extends ReactiveCrudRepository<Relationship,Long> {
}

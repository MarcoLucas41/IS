package org.example.repositories;
import org.example.entities.Consumer;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ConsumerRepository extends ReactiveCrudRepository<Consumer,Long>{


}

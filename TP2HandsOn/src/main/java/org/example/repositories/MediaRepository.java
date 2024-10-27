package org.example.repositories;
import org.example.models.Media;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MediaRepository extends ReactiveCrudRepository<Media,Long>{

}


package org.example.repositories;
import org.example.models.Consumer;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConsumerRepository extends ReactiveCrudRepository<Consumer,Long>{


}

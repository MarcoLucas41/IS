package org.example.repositories;
import org.example.entities.Consumer;
import org.example.entities.Media;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface MediaRepository extends ReactiveCrudRepository<Media,Long>{

    @Query("Select * from consumer where mediaId = $1")
    Flux<Consumer> findConsumerByMediaId(String mediaId);

    @Query("SELECT COUNT(DISTINCT media_id) FROM relationship WHERE rating IS NOT NULL")
    Mono<Long> countSubscribedMedia();
}


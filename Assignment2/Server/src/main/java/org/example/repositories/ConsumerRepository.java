package org.example.repositories;
import org.example.dtos.ConsumerWithMedia;
import org.example.entities.Consumer;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface ConsumerRepository extends ReactiveCrudRepository<Consumer,Long>{

    @Query("""
           SELECT c.id, c.name,c.age, c.gender,
                  array_agg(cm.media_id) AS media_ids,
                  array_agg(m.title) AS media_titles
           FROM consumer c
           LEFT JOIN consumer_media cm ON c.id = cm.consumer_id
           LEFT JOIN media m ON cm.media_id = m.id
           GROUP BY c.id, c.name, c.age, c.gender
           """)
    Flux<ConsumerWithMedia> findAllConsumersWithMediaIds();
}

//@Query("""
//           SELECT c.id, c.name, c.age, c.gender, array_agg(cm.media_id) AS media_ids
//           FROM consumer c
//           LEFT JOIN consumer_media cm ON c.id = cm.consumer_id
//           GROUP BY c.id, c.name, c.age, c.gender
//           """)
package org.example.repositories;
import org.example.models.Consumers;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ConsumerRepository implements ReactiveCrudRepository<Consumers,Long>{

    /**
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends Consumers> Mono<S> save(S entity) {
        return null;
    }

    /**
     * @param entities
     * @param <S>
     * @return
     */
    @Override
    public <S extends Consumers> Flux<S> saveAll(Iterable<S> entities) {
        return null;
    }

    /**
     * @param entityStream
     * @param <S>
     * @return
     */
    @Override
    public <S extends Consumers> Flux<S> saveAll(Publisher<S> entityStream) {
        return null;
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public Mono<Consumers> findById(Long aLong) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Mono<Consumers> findById(Publisher<Long> id) {
        return null;
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public Mono<Boolean> existsById(Long aLong) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Mono<Boolean> existsById(Publisher<Long> id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Flux<Consumers> findAll() {
        return null;
    }

    /**
     * @param longs
     * @return
     */
    @Override
    public Flux<Consumers> findAllById(Iterable<Long> longs) {
        return null;
    }

    /**
     * @param idStream
     * @return
     */
    @Override
    public Flux<Consumers> findAllById(Publisher<Long> idStream) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Mono<Long> count() {
        return null;
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public Mono<Void> deleteById(Long aLong) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Mono<Void> deleteById(Publisher<Long> id) {
        return null;
    }

    /**
     * @param entity
     * @return
     */
    @Override
    public Mono<Void> delete(Consumers entity) {
        return null;
    }

    /**
     * @param longs
     * @return
     */
    @Override
    public Mono<Void> deleteAllById(Iterable<? extends Long> longs) {
        return null;
    }

    /**
     * @param entities
     * @return
     */
    @Override
    public Mono<Void> deleteAll(Iterable<? extends Consumers> entities) {
        return null;
    }

    /**
     * @param entityStream
     * @return
     */
    @Override
    public Mono<Void> deleteAll(Publisher<? extends Consumers> entityStream) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Mono<Void> deleteAll() {
        return null;
    }
}

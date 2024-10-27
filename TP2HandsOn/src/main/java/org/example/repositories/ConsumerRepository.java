package org.example.repositories;
import org.example.models.Consumer;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ConsumerRepository implements ReactiveCrudRepository<Consumer,Long>{

    /**
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    public <S extends Consumer> Mono<S> save(S entity) {
        return null;
    }

    /**
     * @param entities
     * @param <S>
     * @return
     */
    @Override
    public <S extends Consumer> Flux<S> saveAll(Iterable<S> entities) {
        return null;
    }

    /**
     * @param entityStream
     * @param <S>
     * @return
     */
    @Override
    public <S extends Consumer> Flux<S> saveAll(Publisher<S> entityStream) {
        return null;
    }

    /**
     * @param aLong
     * @return
     */
    @Override
    public Mono<Consumer> findById(Long aLong) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Mono<Consumer> findById(Publisher<Long> id) {
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
    public Flux<Consumer> findAll() {
        return null;
    }

    /**
     * @param longs
     * @return
     */
    @Override
    public Flux<Consumer> findAllById(Iterable<Long> longs) {
        return null;
    }

    /**
     * @param idStream
     * @return
     */
    @Override
    public Flux<Consumer> findAllById(Publisher<Long> idStream) {
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
    public Mono<Void> delete(Consumer entity) {
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
    public Mono<Void> deleteAll(Iterable<? extends Consumer> entities) {
        return null;
    }

    /**
     * @param entityStream
     * @return
     */
    @Override
    public Mono<Void> deleteAll(Publisher<? extends Consumer> entityStream) {
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

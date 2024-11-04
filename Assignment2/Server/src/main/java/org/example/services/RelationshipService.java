package org.example.services;

import org.example.entities.Relationship;
import org.example.repositories.ConsumerRepository;
import org.example.repositories.MediaRepository;
import org.example.repositories.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RelationshipService
{
    @Autowired
    private RelationshipRepository rr;
    @Autowired
    private ConsumerRepository cr;
    @Autowired
    private MediaRepository mr;

    public Mono<Relationship> createRelationship(Relationship r)
    {
        return rr.save(r);
    }

    public Flux<Relationship> getAllRelationships()
    {
        return rr.findAll();
    }
    public Mono<Relationship> readRelationship(Long id1, Long id2)
    {
        return rr.findAll()
                .filter(r -> r.getConsumerId().equals(id1) && r.getMediaId().equals(id2))
                .last();
    }

    public Mono<Void> deleteRelationship(Long id1, Long id2)
    {
        return rr.findAll()
                .filter(r -> r.getConsumerId().equals(id1) && r.getMediaId().equals(id2))
                .flatMap(rr::delete)
                .then();
    }
}

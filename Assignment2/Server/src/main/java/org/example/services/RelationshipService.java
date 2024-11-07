package org.example.services;

import org.example.entities.Relationship;
import org.example.repositories.ConsumerRepository;
import org.example.repositories.MediaRepository;
import org.example.repositories.RelationshipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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

    private static final Logger logger = LoggerFactory.getLogger(Relationship.class);

    public Mono<Relationship> createRelationship(Relationship r)
    {
        logger.info("Creating relationship with: {} and {}", r.getConsumerId(), r.getMediaId());
        return rr.save(r);
    }



    public Flux<Long> getAllMediasByConsumerID(Long id)
    {
        logger.info("Getting all medias by Consumer ID: {}", id);
        return rr.findAll()
                .filter(relationship -> relationship.getConsumerId() == id)
                .map(Relationship::getMediaId);
    }

    public Flux<Long> getAllConsumersByMediaID(Long id)
    {
        logger.info("Getting all consumers by Media ID: {}", id);
        return rr.findAll()
                 .filter(relationship -> relationship.getMediaId() == id)
                 .map(Relationship::getConsumerId);
    }




    public Flux<Relationship> getAllRelationships()
    {
        logger.info("Getting all relationships");
        return rr.findAll();
    }
    public Mono<Relationship> readRelationship(Long id1, Long id2)
    {
        logger.info("Reading relationships by IDs: {} and {}", id1, id2);
        return rr.findAll()
                .filter(r -> r.getConsumerId().equals(id1) && r.getMediaId().equals(id2))
                .last();
    }

    public Mono<Void> deleteRelationship(Long id1, Long id2)
    {
        logger.info("Deleting relationship by IDs: {} and {}", id1, id2);
        return rr.findAll()
                .filter(r -> r.getConsumerId().equals(id1) && r.getMediaId().equals(id2))
                .flatMap(rr::delete)
                .then();
    }
}

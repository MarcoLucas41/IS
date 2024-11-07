package org.example.services;

import org.example.entities.Media;
import org.example.repositories.MediaRepository;
<<<<<<< HEAD
=======
import org.example.repositories.RelationshipRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> 29bcb0080b02c1999d850d8f2305fb536d5b389f
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mr;
    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);


    public Mono<Media> saveMedia(Media m) {
        logger.info("Saving media: {}", m.getTitle());
        return mr.save(m);
    }

    public Flux<Media> getAllMedia() {
        logger.info("Getting all Media");
        return mr.findAll();
    }

    public Mono<Media> getMedia(Long id) {
        logger.info("Getting media by ID: {}", id);
        return mr.findById(id);
    }
<<<<<<< HEAD
=======
    public Mono<Long> getSubscribedMediaCount() {
        logger.info("Getting total count of subscribed media");
        return mr.countSubscribedMedia();
    }
>>>>>>> 29bcb0080b02c1999d850d8f2305fb536d5b389f
    public Mono<Media> updateMedia(Media m) {
        logger.info("Updating media: {}", m.getTitle());
        return mr.save(m);
    }

    public Mono<Void> deleteMedia(Long id) {
        logger.info("Deleting media by ID: {}", id);
        return mr.deleteById(id);
    }
}
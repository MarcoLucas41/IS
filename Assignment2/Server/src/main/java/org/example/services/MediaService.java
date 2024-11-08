package org.example.services;

import org.example.entities.Media;
import org.example.repositories.MediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mr;
    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);

    private Mono<Boolean> mediaExists(Long id) {
        logger.info("Verifying if a media exists by ID: {}", id);
        return mr.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new IllegalArgumentException());
            } else {
                sink.next(exists);
            }
        });
    }


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
    public Mono<Media> updateMedia(Media m) {
        logger.info("Updating media: {}", m.getTitle());
        return mediaExists(m.getId()).then(mr.save(m));
    }

    public Mono<Void> deleteMedia(Long id) {
        logger.info("Deleting media by ID: {}", id);
        return mr.deleteById(id);
    }

    public Mono<String> simulateFailure() {
        // Introduce a random failure for testing
        if (Math.random() > 0.5) {
            return Mono.error(new RuntimeException("Simulated network failure"));
        } else {
            return Mono.just("Success");
        }
    }
}
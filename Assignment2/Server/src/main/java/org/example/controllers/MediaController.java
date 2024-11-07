package org.example.controllers;

import org.example.entities.Media;
import org.example.entities.Relationship;
import org.example.services.MediaService;
import org.example.services.RelationshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;


@RestController
@RequestMapping("/media")
public class MediaController
{
    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

    @Autowired
    private MediaService ms;

    @PostMapping
    private Mono<Media> createMedia(@RequestBody Media m)
    {
        logger.info("Request to create media: {}", m.getId());
        return ms.saveMedia(m);
    }
    @GetMapping
    private Flux<Media> getAllMedia()
    {
        logger.info("Request to get all media");
        return ms.getAllMedia();
    }
    @GetMapping("/{id}")
    private Mono<Media> getMedia(@PathVariable("id") Long id)
    {
        logger.info("Request to get media by ID: {}", id);
        return ms.getMedia(id);
    }

    @GetMapping("/getSubscribedCount")
    private Mono<Long> getSubscribedMediaCount()
    {
        logger.info("Request to get the total count of media that are subscribed");
        return ms.getSubscribedMediaCount();
    }
    @PutMapping
    private Mono<Media> updateMedia(@RequestBody Media m)
    {
        logger.info("Request to update media with: {}", m.getTitle());
        return ms.updateMedia(m);
    }
    @DeleteMapping("/{id}")
    private Mono<Void> deleteMedia(@PathVariable("id") Long id)
    {
        logger.info("Request to delete media by ID: {}", id);
        return ms.deleteMedia(id);
    }

//    @GetMapping("/relationships")
//    public Flux<Relationship> getAllRelationships() {
//        return ms.getAllRelationships();
//    }
//    @GetMapping("/subscribed/count")
//    private Mono<Long> getSubscribedMediaCount() {
//        return ms.getSubscribedMediaCount();
//    }
//
//    @GetMapping("/average-users-per-media")
//    public Mono<Double> getAverageUsersPerMedia() {
//        return ms.calculateAverageUsersPerMedia();
//    }
//
//    @GetMapping("/ratings/stats")
//    private Mono<Double> getAverageMediaRating() {
//        return ms.getAverageMediaRating();
//    }

}

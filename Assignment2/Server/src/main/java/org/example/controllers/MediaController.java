package org.example.controllers;

import org.example.entities.Media;
import org.example.services.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


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
    @GetMapping("/test-retry")
    public Mono<String> testRetry() {
        return ms.simulateFailure();
    }


}

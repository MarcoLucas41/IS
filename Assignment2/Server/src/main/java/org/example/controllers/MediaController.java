package org.example.controllers;

import org.example.entities.Media;
import org.example.entities.Relationship;
import org.example.services.MediaService;
import org.example.services.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/media")
public class MediaController
{

    @Autowired
    private MediaService ms;

    @PostMapping
    private Mono<Media> createMedia(@RequestBody Media m)
    {
        return ms.saveMedia(m);
    }
    @GetMapping
    private Flux<Media> getAllMedia()
    {
        return ms.getAllMedia();
    }
    @GetMapping("/{id}")
    private Mono<Media> getMedia(@PathVariable("id") Long id)
    {
        return ms.getMedia(id);
    }

    @PutMapping
    private Mono<Media> updateMedia(@RequestBody Media m)
    {
        return ms.updateMedia(m);
    }
    @DeleteMapping("/{id}")
    private Mono<Void> deleteMedia(@PathVariable("id") Long id)
    {
        return ms.deleteMedia(id);
    }


}
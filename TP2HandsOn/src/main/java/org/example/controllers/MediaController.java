package org.example.controllers;

import org.example.models.Consumers;
import org.example.models.Media;
import org.example.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/media")
public class MediaController
{

    @Autowired
    private MediaService ms;

    @PostMapping
    private int createMedia(Media m)
    {
        return ms.createMedia(m);
    }
    @GetMapping
    private Flux<Media> getAllMedia()
    {
        return ms.getAllMedia();
    }
    @PutMapping
    private int updateSpecificMedia()
    {
        return ms.updateMedia();
    }
    @DeleteMapping
    private int deleteSpecificMedia()
    {
        return ms.deleteSpecificMedia();
    }


    @PostMapping
    private int createRelationship(Media m, Consumers c)
    {
        return ms.createRelationship(m,c);
    }

    @DeleteMapping
    private int deleteRelationship()
    {
        return ms.deleteRelationship();
    }

    @GetMapping
    private Flux<Media> readRelationship()
    {
        return ms.getAllConsumers();
    }




}

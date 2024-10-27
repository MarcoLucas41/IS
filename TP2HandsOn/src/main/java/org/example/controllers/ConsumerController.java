package org.example.controllers;

import org.example.models.Consumers;
import org.example.models.Media;
import org.example.services.ConsumerService;
import org.example.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/consumers")
public class ConsumerController
{
    @Autowired
    private ConsumerService cs;
    @PostMapping
    private int createConsumer(Consumers c)
    {
        return cs.createConsumer(c);
    }
    @GetMapping
    private Flux<Media> getAllConsumer()
    {
        return cs.getAllConsumers();
    }
    @PutMapping
    private int updateSpecificConsumer(Consumers c)
    {
        return cs.updateConsumer(c);
    }
    @DeleteMapping
    private int deleteSpecificConsumer(Consumers c)
    {
        return cs.deleteSpecificConsumer(c);
    }
}

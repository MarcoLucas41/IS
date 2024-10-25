package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService ms;

    @GetMapping
    private Flux<Media> getAllMedia()
    {
        return ms.getAllMedia();
    }
}

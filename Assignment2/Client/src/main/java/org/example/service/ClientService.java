package org.example.service;

import org.example.models.Consumer;
import org.example.models.Media;
import org.example.models.Relationship;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;


public class ClientService
{
    private final WebClient webClient;

    public ClientService(WebClient client) {
        this.webClient = client;
    }

    // 1. Titles and release dates of all media
    public Flux<Media> getTitlesAndReleaseDates() {
        return webClient.get()
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class) // Convert the response to a Flux of Owners
                .map(media -> new Media(media.getTitle(), media.getRelease_date()))
                .retry(3);
    }

    // 2. Number of Media Items
    public Mono<Long> getNumberOfMediaItems() {
        return webClient.get()
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class)
                .count();

    }

    // 3. Number of Media with average rating above 8
    public Mono<Long> getNumberOfMediaWithAvgRatingAbove8()
    {
        return webClient.get()
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class)
                .filter(media -> media.getAvg_rating() > 8)
                .count();
    }

    // 4. Total count of media items that are subscribed
    public Flux<Relationship> getAllRelationships()
    {
        return webClient.get()
                .uri("/consumer/getAllRelationships")
                .retrieve()
                .bodyToFlux(Relationship.class);

    }

    // 5. Data of media items that are from the 80’s
    public Flux<Media> getDataFrom80s()
    {
        return webClient.get()
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class)
                .filter(media -> media.getRelease_date().isBefore(LocalDate.of(1990, 1, 1)) && media.getRelease_date().isAfter(LocalDate.of(1979, 12, 31)))
                .sort( (i1,i2) -> i1.getAvg_rating() > i2.getAvg_rating() ? -1 : i1.getAvg_rating()==i2.getAvg_rating() ? 0 : 1);
    }



    // 6. Average and standard deviation of all media items ratings


    // 7. Name of the oldest media item
    public Flux<Media> getNameOldestMedia()
    {
        return webClient.get()
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class)
                .sort( (i1,i2) -> i1.getRelease_date().isBefore(i2.getRelease_date()) ? -1 : (i1.getRelease_date().isEqual(i2.getRelease_date())) ? 0 : 1)
                .take(1);
    }

    // 8. Average number of users per media item
    // 9. Name and number of users per media item
    // 10. Complete data of all users

    public Flux<Consumer> getConsumersData()
    {
        return webClient.get()
                .uri("/consumer/with-media")
                .retrieve()
                .bodyToFlux(Consumer.class);
    }









}

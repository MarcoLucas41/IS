package org.example.service;

import org.example.ConsumerWithMedia;
import org.example.MediaUsersInfo;
import org.example.Stats;
import org.example.models.Consumer;
import org.example.models.Media;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;



public class ClientService
{
    private final WebClient webClient;

    public ClientService(WebClient client)
    {
        this.webClient = client;

    }

    // 1. Titles and release dates of all media
    public Flux<Media> getTitlesAndReleaseDates() {
        return webClient.get()
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class) // Convert the response to a Flux of Owners
                .map(media -> new Media(media.getTitle(), media.getRelease_date()));
    }

    // 2. Number of Media Items
    public Mono<Long> getNumberOfMediaItems()
    {
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
    public Mono<Long> getCountSubscribedMedia()
    {

        return webClient.get()
                        .uri("/consumer") // Get all Consumers
                        .retrieve()
                        .bodyToFlux(Consumer.class)
                        .map(Consumer::getId)
                        .flatMap(id ->
                                webClient.get()
                                .uri("/relationship/consumer/" + id) // Get media IDs for each consumer
                                .retrieve()
                                .bodyToFlux(Long.class))
                        .distinct() // Ensure unique media IDs across all consumers
                        .count();   // Count
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
    public Mono<Stats> getAveragesAndStandardDeviations()
    {
        return webClient.get()
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class)
                .map(Media::getAvg_rating)
                .reduce(new StatsAccumulator(), StatsAccumulator::accumulate) // Accumulate stats
                .map(StatsAccumulator::calculateStats); // Finalize calculations
    }

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
    public Mono<Double> getAverageNumberUsersPerMedia()
    {
        return webClient.get()
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class)
                .map(Media::getId)
                .flatMap(id ->
                             webClient.get()
                            .uri("/relationship/media/" + id) // Get consumer IDs for each media
                            .retrieve()
                            .bodyToFlux(Long.class)
                                     .count())
                .reduce(new long[]{0, 0}, (accumulator, count) -> {
                    // accumulator[0] holds the sum of consumer counts
                    // accumulator[1] holds the number of media items processed
                    accumulator[0] += count; // Add current media's consumer count to total sum
                    accumulator[1] += 1; // Increment media item count
                    return accumulator;
                })
                .map(accumulator -> {
                    long totalConsumers = accumulator[0];
                    long mediaCount = accumulator[1];
                    return mediaCount == 0 ? 0.0 : (double) totalConsumers / mediaCount;
                });

    }
    // 9. Name and number of users per media item
    public Flux<MediaUsersInfo> getUsersPerMediaItem()
    {
        return webClient.get()
                .uri("/media")
                .retrieve()
                .bodyToFlux(Media.class)
                .flatMap(media ->
                        webClient.get()
                                .uri("/relationship/media/" + media.getId()) // Get consumer IDs for each media
                                .retrieve()
                                .bodyToFlux(Long.class)
                                .flatMap(consumerId ->
                                        webClient.get()
                                                .uri("/consumer/" + consumerId) // Get Consumer details
                                                .retrieve()
                                                .bodyToMono(Consumer.class)
                                )
                                .sort(Comparator.comparingInt(Consumer::getAge).reversed()) // Sort consumers by age descending
                                .reduce(new MediaUsersInfo(media.getTitle(), 0, new ArrayList<>()),
                                        (mediaInfo, consumer) -> {
                                            mediaInfo.addConsumer(consumer);
                                            return mediaInfo;
                                        }
                                )
                );
    }

    // 10. Complete data of all users
    public Flux<ConsumerWithMedia> getConsumersData()
    {
        return webClient.get()
                        .uri("/consumer")
                        .retrieve()
                        .bodyToFlux(Consumer.class)
                        .flatMap(consumer ->
                                webClient.get()
                                            .uri("/relationship/consumer/" + consumer.getId()) // Get consumer IDs for each media
                                            .retrieve()
                                            .bodyToFlux(Long.class)
                                            .flatMap(mediaId ->
                                                    webClient.get()
                                                            .uri("/media/" +mediaId) // Get Consumer details
                                                            .retrieve()
                                                            .bodyToMono(Media.class)
                                            )
                                    .reduce(new ConsumerWithMedia(consumer.getId(), consumer.getName(),consumer.getAge(),consumer.getGender(),new ArrayList()),
                                            (mediaInfo, c) -> {
                                                mediaInfo.addMediaTitle(c.getTitle());
                                                return mediaInfo;
                                            })
                        );
    }
}

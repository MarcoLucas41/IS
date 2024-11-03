package org.example.services;

import org.example.entities.Media;
import org.example.entities.Relationship;
import org.example.repositories.MediaRepository;
import org.example.repositories.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MediaService
{
    @Autowired
    private MediaRepository mr;

    @Autowired
    private RelationshipRepository rr;
    public Mono<Media> saveMedia(Media m)
    {
        return mr.save(m);
    }
    public Flux<Media> getAllMedia()
    {
        return mr.findAll() // Retrieve all media items
                .flatMap(media ->
                        getMediaAvgRating(media.getId()) // Calculate average rating for each media item
                                .map(avgRating -> {
                                    media.setAvg_rating(avgRating); // Set the average rating in the media object
                                    return media;                 // Return the updated media object
                                })
                );
    }

    public Mono<Media> getMedia(Long id)
    {
        return mr.findById(id)
                 .flatMap(media ->
                    getMediaAvgRating(media.getId())
                            .map(avgRating -> {
                                media.setAvg_rating(avgRating);
                                return media;
                            })
    );
    }
    public Mono<Media>  updateMedia(Media m) {
        return mr.save(m);
    }

    public Mono<Void> deleteMedia(Long id) {
        return mr.deleteById(id);
    }


    public Mono<Long> getSubscribedMediaCount() {
        return mr.countSubscribedMedia();
    }

//    public int createRelationship(Media m, Consumer c)
//    {
//        return 0;
//    }
//    public int deleteRelationship() {
//        return 0;
//    }
    public Mono<Float> getMediaAvgRating(Long id1)
    {
        return rr.findAll()
                .filter(r -> r.getMediaId().equals(id1))
                .map(Relationship::getRating)
                .reduce(new RatingAccumulator((float) 0, 0),     // Initial accumulator with sum 0, count 0
                        (accumulator, rating) -> new RatingAccumulator(
                                 accumulator.sum+rating,
                                accumulator.count + 1
                        ))                               // Accumulate sum and count
                .flatMap(accumulator -> {
                    if (accumulator.count == 0) {
                        return Mono.just(0.0f);           // Handle case where no ratings were found
                    }
                    float avg =  accumulator.sum / accumulator.count;
                    return Mono.just(avg);               // Calculate and return the average
                });
    }

    public Mono<Double> calculateAverageUsersPerMedia() {
        return mr.findAll()
                .flatMap(media -> mr.countConsumersByMediaId(media.getId())) // Adjust method name if needed
                .collectList()
                .map(counts -> {
                    double sum = counts.stream().mapToInt(count -> count).sum();
                    return sum / counts.size();
                });
    }
}

// Helper class to hold the sum and count
class RatingAccumulator {
    float sum;
    int count;

    RatingAccumulator(float sum, int count) {
        this.sum = sum;
        this.count = count;
    }
}
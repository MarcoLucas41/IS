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
        return mr.findAll();
    }

    public Mono<Media> getMedia(Long id)
    {
        return mr.findById(id);
//                 .flatMap(media ->
//                    getMediaAvgRating(media.getId())
//                            .map(avgRating -> {
//                                media.setAvg_rating(avgRating);
//                                return media;
//                            })
//    );
    }
    public Mono<Media>  updateMedia(Media m) {
        return mr.save(m);
    }

    public Mono<Void> deleteMedia(Long id) {
        return mr.deleteById(id);
    }

//    public Mono<Float> getMediaAvgRating(Long id1)
//    {
//        return rr.findAll()
//                .filter(r -> r.getMediaId().equals(id1))
//                .map(Relationship::getRating)
//                .reduce(new RatingAccumulator((float) 0, 0),     // Initial accumulator with sum 0, count 0
//                        (accumulator, rating) -> new RatingAccumulator(
//                                 accumulator.sum+rating,
//                                accumulator.count + 1
//                        ))                               // Accumulate sum and count
//                .flatMap(accumulator -> {
//                    if (accumulator.count == 0) {
//                        return Mono.just(0.0f);           // Handle case where no ratings were found
//                    }
//                    float avg =  accumulator.sum / accumulator.count;
//                    return Mono.just(avg);               // Calculate and return the average
//                });
//    }
}
//
//// Helper class to hold the sum and count
//class RatingAccumulator {
//    float sum;
//    int count;
//
//    RatingAccumulator(float sum, int count) {
//        this.sum = sum;
//        this.count = count;
//    }
//}
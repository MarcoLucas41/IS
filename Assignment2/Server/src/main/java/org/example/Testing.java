package org.example;

import lombok.ToString;
import org.example.entities.Consumer;
import org.example.entities.Media;
import org.example.entities.Relationship;
import org.json.JSONObject;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class Testing
{
    interface MyComparator {
        int compareTo(int i1, int i2);

    }

    public static void main(String[] args)
    {

        String CONSUMER_ID = "5";
        String MEDIA_ID = "1";
        String BASE_URL = "http://localhost:8080";
        String GET_ALL_MEDIA_URI = "/media";
        String GET_MEDIA_URI = "/media/"+MEDIA_ID;
        String GET_ALL_CONSUMERS_URI = "/consumer";
        String GET_CONSUMER_URI = "/consumer/"+CONSUMER_ID;

        String RELATIONSHIP_CREATE_URI = "/consumer/createRelationship/"+CONSUMER_ID+"/"+MEDIA_ID+"/"+8;
        String RELATIONSHIP_READ_URI = "/consumer/readRelationship/2/6";
        String RELATIONSHIP_DELETE_URI = "/consumer/deleteRelationship/"+CONSUMER_ID+"/"+MEDIA_ID;
        String GET_ALL_RELATIONSHIPS = "/consumer/getAllRelationships";



        //***** WORKS *****
        //GET ALL MEDIA
        WebClient.create(BASE_URL)
                .get()
                .uri(GET_ALL_MEDIA_URI)
                .retrieve()
                .bodyToFlux(Media.class)
                .subscribe(System.out::println);

//        //GET ONE MEDIA
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_MEDIA_URI)
//                .retrieve()
//                .bodyToFlux(Media.class)
//                .subscribe(System.out::println);

        //GET ALL CONSUMERS
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_ALL_CONSUMERS_URI)
//                .retrieve()
//                .bodyToFlux(Consumer.class)
//                .subscribe(System.out::println);

//        //GET ONE CONSUMER
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_CONSUMER_URI)
//                .retrieve()
//                .bodyToMono(Consumer.class)
//                .subscribe(System.out::println);

//        //  POST ONE MEDIA
//        Media series = new Media("The Walking Dead",LocalDate.of(2010, 10, 31),"TV Show");
//
//        WebClient.create(BASE_URL)
//                .post()
//                .uri(GET_ALL_MEDIA_URI)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(series)
//                .retrieve()
//                .toEntity(Media.class)
//                .subscribe(System.out::println);
//
//         // POST ONE CONSUMER
//        Consumer michael = new Consumer("Michael", 20,"Male");
//
//        WebClient.create(BASE_URL)
//                .post()
//                .uri(GET_ALL_CONSUMERS_URI)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(michael)
//                .retrieve()
//                .toEntity(Consumer.class)
//                .subscribe(System.out::println);
        //DELETE ONE MEDIA
//        WebClient.create(BASE_URL)
//                 .delete()
//                 .uri(GET_MEDIA_URI)
//                 .retrieve()
//                 .toEntity(Media.class)
//                 .subscribe(System.out::println);
        // DELETE ONE CONSUMER
//        WebClient.create(BASE_URL)
//                 .delete()
//                 .uri(GET_CONSUMER_URI)
//                 .retrieve()
//                 .toEntity(Consumer.class)
//                 .subscribe(System.out::println);


        //CREATE RELATIONSHIP
//        WebClient.create(BASE_URL)
//                .post()
//                .uri(RELATIONSHIP_CREATE_URI)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .toEntity(Relationship.class)
//                .subscribe(System.out::println);
//
        //GET ALL RELATIONSHIPS
        WebClient.create(BASE_URL)
                .get()
                .uri(GET_ALL_RELATIONSHIPS)
                .retrieve()
                .bodyToFlux(Relationship.class)
                .subscribe(System.out::println);

        //READ RELATIONSHIP
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(RELATIONSHIP_READ_URI)
//                .retrieve()
//                .bodyToMono(Relationship.class)
//                .subscribe(System.out::println);

        //DELETE RELATIONSHIP

//        WebClient.create(BASE_URL)
//                .delete()
//                .uri(RELATIONSHIP_DELETE_URI)
//                .retrieve()
//                .toEntity(Relationship.class)
//                .subscribe(System.out::println);



        String OUTPUT1= "output.txt";
        String OUTPUT2= "output2.txt";
        String OUTPUT3= "output3.txt";
        String OUTPUT5= "output5.txt";
        String OUTPUT7 = "output7.txt";

//        // #1
//        WebClient.create(BASE_URL)
//                    .get()
//                    .uri(GET_ALL_MEDIA_URI)
//                    .retrieve()
//                    .bodyToFlux(Media.class)
//                    .subscribe(
//                            media -> writeToFile(media.getTitle()+"||"+media.getRelease_date().toString(), OUTPUT1),
//                            error -> System.err.println("Error retrieving media: " + error.getMessage()),
//                            () -> System.out.println("Data retrieval complete.")
//                    );
//        // #2
//        WebClient.create(BASE_URL)
//                    .get()
//                    .uri(GET_ALL_MEDIA_URI)
//                    .retrieve()
//                    .bodyToFlux(Media.class)
//                    .count()
//                    .subscribe(
//                        count -> writeToFile(count.toString(), OUTPUT2),
//                        error -> System.err.println("Error retrieving the number of media items: " + error.getMessage()),
//                        () -> System.out.println("Data retrieval complete.")
//                );
//        // #3
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_ALL_MEDIA_URI)
//                .retrieve()
//                .bodyToFlux(Media.class)
//                .filter(media -> media.getAvg_rating() > 8)
//                .count()
//                .subscribe(
//                        count -> writeToFile(count.toString(), OUTPUT3),
//                        error -> System.err.println("Error retrieving the media items with average rating above 8: " + error.getMessage()),
//                        () -> System.out.println("Data retrieval complete.")
//                );

        // #4 (POR FAZER)
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_ALL_RELATIONSHIPS)
//                .retrieve()
//                .bodyToFlux(Relationship.class)
//                .map(media -> media.)
//                .subscribe(
//                        count -> writeToFile(count.toString(), OUTPUT3),
//                        error -> System.err.println("Error retrieving the media items with average rating above 8: " + error.getMessage()),
//                        () -> System.out.println("Data retrieval complete.")
//                );

//        // #5
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_ALL_MEDIA_URI)
//                .retrieve()
//                .bodyToFlux(Media.class)
//                .filter(media -> media.getRelease_date().isBefore(LocalDate.of(1990, 1, 1)) && media.getRelease_date().isAfter(LocalDate.of(1979, 12, 31)))
//                .subscribe(System.out::println);

//
//
//        // #7
//
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(GET_ALL_MEDIA_URI)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToFlux(Media.class)
//                .sort( (i1,i2) -> i1.getRelease_date().isBefore(i2.getRelease_date()) ? -1 : (i1.getRelease_date().isEqual(i2.getRelease_date())) ? 0 : 1)
//                .take(1)
//                .subscribe(
//                        media -> writeToFile(media, OUTPUT7),
//                        error -> System.err.println("Error retrieving media: " + error.getMessage()),
//                        () -> System.out.println("Data retrieval complete.")
//                );

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }







    }
    private static void writeToFile(String data, String filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath),
                Files.exists(Paths.get(filePath)) ?
                        StandardOpenOption.APPEND :
                        StandardOpenOption.CREATE)) {
            writer.write(data); // Use an appropriate method to format Media
            writer.newLine(); // Add a new line after each media item
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

package org.example;

import org.example.entities.Consumer;
import org.example.entities.Media;
import org.example.entities.Relationship;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class Testing
{
    public static void main(String[] args)
    {

        String CONSUMER_ID = "5";
        String MEDIA_ID = "1";
        String BASE_URL = "http://localhost:8080";
        String GET_ALL_MEDIA_URI = "/media";
        String GET_MEDIA_URI = "/media/"+MEDIA_ID;
        String GET_ALL_CONSUMERS_URI = "/consumer";
        String GET_CONSUMER_URI = "/consumer/"+CONSUMER_ID;

        String RELATIONSHIP_CREATE_URI = "/relationship/create/"+CONSUMER_ID+"/"+MEDIA_ID+"/"+8;
        String RELATIONSHIP_READ_CONSUMER_URI = "/relationship/consumer/2";
        String RELATIONSHIP_READ_MEDIA_URI = "/relationship/consumer/2";
        String RELATIONSHIP_DELETE_URI = "/consumer/deleteRelationship/"+CONSUMER_ID+"/"+MEDIA_ID;

        //GET ALL MEDIA
        WebClient.create(BASE_URL)
                .get()
                .uri(GET_ALL_MEDIA_URI)
                .retrieve()
                .bodyToFlux(Media.class)
                .subscribe(System.out::println);

         //GET ONE MEDIA
        WebClient.create(BASE_URL)
                .get()
                .uri(GET_MEDIA_URI)
                .retrieve()
                .bodyToFlux(Media.class)
                .subscribe(System.out::println);

        //GET ALL CONSUMERS
        WebClient.create(BASE_URL)
                .get()
                .uri(GET_ALL_CONSUMERS_URI)
                .retrieve()
                .bodyToFlux(Consumer.class)
                .subscribe(System.out::println);

//        //GET ONE CONSUMER
        WebClient.create(BASE_URL)
                .get()
                .uri(GET_CONSUMER_URI)
                .retrieve()
                .bodyToMono(Consumer.class)
                .subscribe(System.out::println);

//        //  POST ONE MEDIA
        Media series = new Media("The Walking Dead",LocalDate.of(2010, 10, 31),"TV Show");
//
        WebClient.create(BASE_URL)
                .post()
                .uri(GET_ALL_MEDIA_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(series)
                .retrieve()
                .toEntity(Media.class)
                .subscribe(System.out::println);
//
//      // POST ONE CONSUMER
        Consumer michael = new Consumer("Michael", 20,"Male");
        WebClient.create(BASE_URL)
                .post()
                .uri(GET_ALL_CONSUMERS_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(michael)
                .retrieve()
                .toEntity(Consumer.class)
                .subscribe(System.out::println);
        //DELETE ONE MEDIA
        WebClient.create(BASE_URL)
                 .delete()
                 .uri(GET_MEDIA_URI)
                 .retrieve()
                 .toEntity(Media.class)
                 .subscribe(System.out::println);
        // DELETE ONE CONSUMER
        WebClient.create(BASE_URL)
                 .delete()
                 .uri(GET_CONSUMER_URI)
                 .retrieve()
                 .toEntity(Consumer.class)
                 .subscribe(System.out::println);


        //CREATE RELATIONSHIP
        WebClient.create(BASE_URL)
                .post()
                .uri(RELATIONSHIP_CREATE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Relationship.class)
                .subscribe(System.out::println);
//

        //READ RELATIONSHIP BY CONSUMER ID
        WebClient.create(BASE_URL)
                .get()
                .uri(RELATIONSHIP_READ_CONSUMER_URI)
                .retrieve()
                .bodyToMono(Long.class)
                .subscribe(System.out::println);

        //READ RELATIONSHIP BY MEDIA ID
        WebClient.create(BASE_URL)
                .get()
                .uri(RELATIONSHIP_READ_MEDIA_URI)
                .retrieve()
                .bodyToMono(Long.class)
                .subscribe(System.out::println);

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }







    }
}

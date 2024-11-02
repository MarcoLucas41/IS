package org.example;

import lombok.ToString;
import org.example.entities.Consumer;
import org.example.entities.Media;
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

public class Client
{
    interface MyComparator {
        int compareTo(int i1, int i2);

    }
    public Mono<Void> writeRows(Flux<String> rowsFlux) {
        DefaultDataBufferFactory bufferFactory = new DefaultDataBufferFactory();
        CharSequenceEncoder encoder = CharSequenceEncoder.textPlainOnly();

        Flux<DataBuffer> dataBufferFlux = rowsFlux.map(line ->
                encoder.encodeValue(line, bufferFactory, ResolvableType.NONE, null, null)
        );
        return DataBufferUtils.write(
                dataBufferFlux,
                Path.of("save.txt"),
                StandardOpenOption.CREATE_NEW
        );
    }

    public static void main(String[] args)
    {

        String CONSUMER_ID = "1";
        String MEDIA_ID = "3";
        String BASE_URL = "http://localhost:8080";
        String GET_ALL_MEDIA_URI = "/media";
        String SUBSCRIBED_MEDIA_COUNT_URI = "/media/subscribed/count";
        String GET_MEDIA_URI = "/media/"+MEDIA_ID;
        String GET_ALL_CONSUMERS_URI = "/consumer";
        String GET_CONSUMER_URI = "/consumer/"+CONSUMER_ID;

        String RELATIONSHIP_URI = "/consumer/"+CONSUMER_ID+"/"+MEDIA_ID;



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


        // ***** DOESNT WORK ******

        //CREATE RELATIONSHIP
//        WebClient.create(BASE_URL)
//                .post()
//                .uri(RELATIONSHIP_URI)
//                .retrieve()
//                .toEntity(Consumer.class)
//                .subscribe(System.out::println);

//        // GET THE SUBSCRIBED MEDIA
//        WebClient.create(BASE_URL)
//                .get()
//                .uri(SUBSCRIBED_MEDIA_COUNT_URI)
//                .retrieve()
//                .bodyToMono(Long.class)
//                .subscribe(
//                        count -> System.out.println("Total subscribed media count: " + count),
//                        error -> System.err.println("Error retrieving subscribed media count: " + error.getMessage()),
//                        () -> System.out.println("Subscribed media count retrieval complete.")
//                );

        //#1
        String OUTPUT_FILE_PATH = "output.txt"; // Change this to your desired output file path
        WebClient.create(BASE_URL)
                .get()
                .uri(GET_ALL_MEDIA_URI)
                .retrieve()
                .bodyToFlux(Media.class)
                .subscribe(
                        media -> writeToFile(media, OUTPUT_FILE_PATH),
                        error -> System.err.println("Error retrieving media: " + error.getMessage()),
                        () -> System.out.println("Data retrieval complete.")
                );


        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void writeToFile(Media media, String filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath),
                Files.exists(Paths.get(filePath)) ?
                        StandardOpenOption.APPEND :
                        StandardOpenOption.CREATE)) {
            writer.write(media.toString()); // Use an appropriate method to format Media
            writer.newLine(); // Add a new line after each media item
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

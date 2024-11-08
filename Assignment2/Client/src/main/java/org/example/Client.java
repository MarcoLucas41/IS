package org.example;


import org.example.models.Consumer;
import org.example.models.Media;
import org.example.service.ClientService;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class Client
{
    static ClientService clientService;
    public static void createFile(String id) {
        try {
            File myObj = new File("req" + id + ".txt");
            myObj.createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void Req1()
    {
        createFile("1");
        try {
            Flux<Media> flux = clientService.getTitlesAndReleaseDates();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req1.txt"));
            flux.doOnNext(owner -> {
                        String content = "Title: " + owner.getTitle() + ", Release Date: "
                                + owner.getRelease_date().toString() + ";\n";
                        try {
                            writer.write(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req1 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Req2()
    {
        createFile("2");
        try {
            Mono<Long> mono = clientService.getNumberOfMediaItems();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req2.txt"));
            mono.doOnNext(total -> {
                        String content = "Total number of media items: " + total.toString() + ";\n";
                        try {
                            writer.write(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req2 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Req3()
    {
        createFile("2");
        try {
            Mono<Long> mono = clientService.getNumberOfMediaWithAvgRatingAbove8();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req3.txt"));
            mono.doOnNext(total -> {
                        String content = "Total number of media items with average rating above 8: " + total.toString() + "\n";
                        try {
                            writer.write(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req3 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Req4()
    {
        createFile("4");
        try {
            Mono<Long> mono = clientService.getCountSubscribedMedia();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req4.txt"));
            mono.doOnNext(total -> {
                        String content = "Total number of subscribed media: " + total.toString() + "\n";
                        try {
                            writer.write(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req4 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Req5()
    {
        createFile("5");
        try {
            Flux<Media> flux = clientService.getDataFrom80s();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req5.txt"));
            flux.doOnNext(media -> {
                        String content = "Title: " + media.getTitle() + ", Release Date: "
                                + media.getRelease_date().toString() + ", Type: " + media.getType()+", Rating: "+media.getAvg_rating()+"\n";
                        try {
                            writer.write(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req5 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Req6()
    {
        createFile("6");
        try {
            Mono<Stats> statsMono = clientService.getAveragesAndStandardDeviations();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req6.txt"));
            statsMono.doOnNext(stats -> {
                        StringBuilder content = new StringBuilder("Average of all media items ratings: " + stats.getAverage() + "\n");
                        content.append("Standard Deviations:\n");
                        List<Double> temp = stats.getStandardDeviations();
                        String userline;
                        for(Double d: temp)
                        {
                            userline = "-- "+d+"\n";
                            content.append(userline);
                        }
                        try {
                            writer.write(content.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req6 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Req7()
    {
        createFile("7");
        try {
            Flux<Media> flux = clientService.getNameOldestMedia();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req7.txt"));
            flux.doOnNext(media -> {
                        String content = "Title: " + media.getTitle()+"\n";
                        try {
                            writer.write(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req7 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void Req8()
    {
        createFile("8");
        try {
            Mono<Double> avgMono = clientService.getAverageNumberUsersPerMedia();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req8.txt"));
            avgMono.doOnNext(avg -> {
                        String content = "Average number of users per media: " + avg+"\n";
                        try {
                            writer.write(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req8 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Req9()
    {
        createFile("9");
        try {
            Flux<MediaUsersInfo> flux = clientService.getUsersPerMediaItem();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req9.txt"));
            flux.doOnNext(mediaUsersInfo -> {
                        StringBuilder content = new StringBuilder("Media: " + mediaUsersInfo.getMediaName() + " --  " + mediaUsersInfo.getUserCount() + "\n");
                        List<Consumer> temp = mediaUsersInfo.getConsumers();
                        String userline;
                        for(Consumer c: temp)
                        {
                            userline = "-- "+ c.getName()+" ("+c.getAge()+")\n";
                            content.append(userline);
                        }
                        try {
                            writer.write(content.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req9 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void Req10()
    {
        createFile("10");
        try {
            Flux<ConsumerWithMedia> flux = clientService.getConsumersData();
            BufferedWriter writer = new BufferedWriter(new FileWriter("req10.txt"));
            flux.doOnNext(consumer -> {
                        String content = "ID: " + consumer.getConsumerId()+"\n"+
                                "-- Name: "+consumer.getName()+"\n"+
                                "-- Age: "+consumer.getAge()+"\n"+
                                "-- Gender: "+consumer.getGender()+"\n";
                        List<String> temp = consumer.getMediaTitles();
                        if(!temp.isEmpty()) content += "-- Subscribed Media Items: "+consumer.getMediaTitles()+"\n";
                        else content += "-- No Subscribed Items\n";
                        try {
                            writer.write(content);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .doFinally(signalType -> {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .subscribe(
                            null,
                            Throwable::printStackTrace, // Handle any errors during the pipeline
                            () -> System.out.println("Req10 completed successfully.") // Completion signal
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void clientRequirements(String[] args)
    {
        WebClient client = WebClient.create("http://localhost:8080");
        clientService = new ClientService(client);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Create an array of CompletableFutures for each Req function
        CompletableFuture<Void> req1 = CompletableFuture.runAsync(() -> Req1(), executor);
        CompletableFuture<Void> req2 = CompletableFuture.runAsync(() -> Req2(), executor);
        CompletableFuture<Void> req3 = CompletableFuture.runAsync(() -> Req3(), executor);
        CompletableFuture<Void> req4 = CompletableFuture.runAsync(() -> Req4(), executor);
        CompletableFuture<Void> req5 = CompletableFuture.runAsync(() -> Req5(), executor);
        CompletableFuture<Void> req6 = CompletableFuture.runAsync(() -> Req6(), executor);
        CompletableFuture<Void> req7 = CompletableFuture.runAsync(() -> Req7(), executor);
        CompletableFuture<Void> req8 = CompletableFuture.runAsync(() -> Req8(), executor);
        CompletableFuture<Void> req9 = CompletableFuture.runAsync(() -> Req9(), executor);
        CompletableFuture<Void> req10 = CompletableFuture.runAsync(() -> Req10(), executor);

        // Wait for all requests to complete
        CompletableFuture.allOf(req1, req2, req3, req4, req5, req6, req7, req8, req9, req10).join();

        // Shutdown the executor to release resources
        executor.shutdown();
    }

    public static void main(String[] args)
    {
        clientRequirements(args);
    }
}

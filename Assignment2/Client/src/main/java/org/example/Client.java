package org.example;

import lombok.ToString;

import org.example.models.Consumer;
import org.example.models.Media;
import org.example.service.ClientService;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.ui.context.support.SimpleTheme;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;

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
                    .subscribe();
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
                    .subscribe();
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
                    .subscribe();
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
                    .subscribe();
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
                    .subscribe();
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
                        String content = "Average: " + stats.getAverage()+ ", Standard Deviation: "
                                + stats.getStandardDeviation()+"\n";
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
                    .subscribe();
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
                    .subscribe();
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
                    .subscribe();
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
                        String content = "Media: " + mediaUsersInfo.getMediaName()+" --  "+mediaUsersInfo.getUserCount()+"\n";
                        List<Consumer> temp = mediaUsersInfo.getConsumers();
                        for(Consumer c: temp)
                        {
                            content += c.getName();
                            content +="\n";
                        }
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
                    .subscribe();
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
                    .subscribe();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clientRequirements() {
        WebClient client = WebClient.create("http://localhost:8080");
        clientService = new ClientService(client);
        Req1();
        Req2();
        Req3();
          Req4();
          Req5();
          Req6();
          Req7();
          Req8();
          Req9();
          Req10();
    }

    public static void main(String[] args)
    {
        System.out.println("IM RUNNING!!!");
        clientRequirements();
    }
}

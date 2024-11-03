package org.example;

import lombok.ToString;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class Client
{
    public static void createFile(String id) {
        try {
            File myObj = new File("resp" + id + ".txt");
            myObj.createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void Req1()
    {

    }
    public static void Req2()
    {

    }
    public static void Req3()
    {

    }
    public static void Req4()
    {

    }
    public static void Req5()
    {

    }
    public static void Req6()
    {

    }
    public static void Req7()
    {

    }
    public static void Req8()
    {

    }
    public static void Req9()
    {

    }
    public static void Req10()
    {

    }

    public static void clientRequirements() {
        Req1();
    }

    public static void main(String[] args)
    {
        ApplicationContext context = SpringApplication.run(Client.class, args);
        clientRequirements();
    }
}

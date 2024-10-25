package org.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.persistence.jaxb.JAXBMarshaller;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.models.Book;
import org.models.Bookstore;
import org.configuration.Configuration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.net.*;
import java.util.Random;
import java.util.Scanner;


public class Server
{
    public static void marshall(Marshaller jaxbMarshaller, File f, Bookstore bs)
    {
        try
        {
            // marshalling
            long start = System.currentTimeMillis();
            jaxbMarshaller.marshal(bs, f);
            long finish = System.currentTimeMillis();
            long duration = finish-start;
            System.out.println("MARSHALLING TIME: "+duration+" ms");

        }catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException, JAXBException
    {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        JAXBContext jaxbContext = null;
        try
        {
            serverSocket = new ServerSocket(4444);
        }
        catch (IOException e) {
            System.out.println("Could not listen on port: 4444");
            System.exit(-1);
        }
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4444");
            System.exit(-1);
        }

        jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                .createContext(new Class[]{Bookstore.class}, null);


        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/xml");
        //jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
        //jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");


        jaxbMarshaller.setProperty("org.glassfish.jaxb.xmlHeaders",
                "<?xml-stylesheet type='text/xsl' href='test.xsl' ?>");

        System.out.println("Server ready");

        // creating java object
        Bookstore bs = new Bookstore();
        ArrayList<Book> bklist = new ArrayList<>();

        for(int i = 0; i < Configuration.NUM_ELEMENTS; i++)
        {
            Book bk = new Book();
            bk.setId(Integer.toString(i));
            bk.setTitle(getTitle());
            //bk.setAuthor(getAuthor());
            bklist.add(bk);
        }
        bs.setBooks(bklist);

        File f = new File("bookstore.xml");
        Path xmlFilePath = Paths.get("bookstore.xml");

        /*
        File f = new File("bookstore.json");
        Path xmlFilePath = Paths.get("bookstore.json");*/
        marshall(jaxbMarshaller,f,bs);

        BufferedInputStream fileInput = new BufferedInputStream(Files.newInputStream(xmlFilePath));
        BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());


        byte[] buffer = new byte[4096]; // 4KB buffer
        int bytesRead;

        // Read from file and write to socket
        while ((bytesRead = fileInput.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        out.flush(); // Ensure all data is sent


        clientSocket.close();
        serverSocket.close();


    }

    private static String getAuthor() {
        return RandomStringUtils.random(128, true, false);
        //return "José Saramago";

    }

    private static String getTitle() {
        return RandomStringUtils.random(27, true, false);
        //return "Os Maias";
    }


}

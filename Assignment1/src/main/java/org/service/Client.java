package org.service;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.configuration.Configuration;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.models.Bookstore;

public class Client
{
    public static Bookstore unmarshall(Unmarshaller jaxbUnmarshaller, File file)
    {

        Bookstore o = null;
        try
        {
            // unmarshalling
            long start = System.currentTimeMillis();
            o = (Bookstore) jaxbUnmarshaller.unmarshal(file);
            long finish = System.currentTimeMillis();
            long duration = finish-start;
            System.out.println("UNMARSHALLING TIME: "+duration+" ms");

        }catch (JAXBException e) {
            e.printStackTrace();
        }
        return o;
    }
    public static void main(String[] args) throws IOException
    {
        Socket echoSocket = null;
        BufferedInputStream in = null;
        BufferedOutputStream fileOut = null;

        Path xmlFilePath = Paths.get("serverResult.xml");
        PrintWriter pw = new PrintWriter("serverResult.xml");

        /*
        Path xmlFilePath = Paths.get("serverResult.json");
        PrintWriter pw = new PrintWriter("serverResult.json");
        */

        JAXBContext jaxbContext = null;
        Unmarshaller jaxbUnmarshaller = null;

        try
        {
            echoSocket = new Socket("localhost", 4444);
            in = new BufferedInputStream(echoSocket.getInputStream());
            fileOut = new BufferedOutputStream(Files.newOutputStream(xmlFilePath));
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{Bookstore.class}, null);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();



            //jaxbUnmarshaller.setProperty("eclipselink.media-type", "application/json");
            //jaxbUnmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, true);


        } catch (UnknownHostException e) {
            System.err.println("Don’t know about server");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn’t get I/O for "
                    + "the connection to server.");
            System.exit(1);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }


        byte[] buffer = new byte[4096]; // 4KB buffer
        int bytesRead;

        // Read from socket and write to file
        while ((bytesRead = in.read(buffer)) != -1) {
            fileOut.write(buffer, 0, bytesRead);
        }
        fileOut.flush();
        Bookstore o = unmarshall(jaxbUnmarshaller,xmlFilePath.toFile());
        pw.close();





        echoSocket.close();
    }

}

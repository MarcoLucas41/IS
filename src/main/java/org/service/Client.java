package org.service;

import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.configuration.Configuration;
import org.models.Bookstore;
import org.models.Message;

public class Client
{
    public static String demarshall(File f)
    {
        JAXBContext jaxbContext;
        Bookstore o = null;
        try
        {
            // setting up jaxb context
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{Bookstore.class}, null);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // unmarshalling
            o = (Bookstore) jaxbUnmarshaller.unmarshal(f);

        }catch (JAXBException e) {
            e.printStackTrace();
        }
        return o.toString();
    }
    public static void main(String[] args) throws RemoteException, InterruptedException
    {
        Message targetMessage = null;
        boolean connected = false;
        long start,finish;
        while (!connected)
        {
            try
            {
                //targetMessage = (Message) Naming.lookup("rmi://"+ Configuration.IP_SERVICE+"/bookstore");
                targetMessage = (Message) Naming.lookup("rmi://localhost:1099/bookstore");
                connected = true;
            }
            catch (Exception e)
            {
                System.err.println("Error connecting to server, retrying in 3 seconds");
                Thread.sleep(3000);
            }
        }
        start = System.currentTimeMillis();
        String message = demarshall(targetMessage.remote_message());
        finish = System.currentTimeMillis();
        System.out.println(message);
        long duration = finish-start;
        System.out.printf("Time: "+duration);
    }

}

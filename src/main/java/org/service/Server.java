package org.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.models.Book;
import org.models.Bookstore;
import org.models.Content;
import org.models.Message;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.lang.instrument.Instrumentation;


public class Server extends UnicastRemoteObject implements Message
{


    protected Server() throws RemoteException {
    }

    public static File marshall(File f, Bookstore bs)
    {
        JAXBContext jaxbContext;
        try
        {
            // setting up jaxb context
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{Bookstore.class}, null);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty("org.glassfish.jaxb.xmlHeaders",
                    "<?xml-stylesheet type='text/xsl' href='test.xsl' ?>");
            // jaxbMarshaller.setProperty("org.glassfish.jaxb.namespacePrefixMapper", new DefaultNamespacePrefixMapper());

            // marshalling
            jaxbMarshaller.marshal(bs, f);

        }catch (JAXBException e) {
            e.printStackTrace();
        }
        return f;
    }


    @Override
    public Content remote_message() throws RemoteException
    {
        // creating java object
        Bookstore bs = new Bookstore();
        ArrayList<Book> bklist = new ArrayList<>();

        Book bk = new Book();
        bk.setId("1");
        bk.setName("Misery");
        bk.setAuthor("Stephen King");
        bklist.add(bk);

        bs.setBooks(bklist);

        File f = new File("bookstore.xml");

        // time measure of MARSHALLING
        long start = System.currentTimeMillis();
        File marshalled = marshall(f,bs);
        long finish = System.currentTimeMillis();
        long duration = finish-start;

        Content content = new Content(marshalled,duration);
        //System.out.printf("Size of Marshalled file: "+ Instrume);
        return content;
    }
    public static void main(String[] args) throws InterruptedException, MalformedURLException
    {
        try
        {
            Server server = new Server();
            LocateRegistry.createRegistry(1099).rebind("bookstore", server);
            System.out.println("Server ready");
            while(true){}
        }
        catch (RemoteException re) {
            System.out.println("Exception in Server.main: " + re);
        }

    }



}

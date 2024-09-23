package org.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.models.Book;
import org.models.Bookstore;


import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;


public class App
{
    public static void main(String[] args) throws RemoteException {


        // creating java object
        Bookstore bs = new Bookstore();
        ArrayList<Book> bklist = new ArrayList<>();

        Book bk = new Book();
        bk.setId("1");
        bk.setName("Misery");
        bk.setAuthor("Stephen King");
        bklist.add(bk);

        bs.setBooks(bklist);


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


            File f = new File("bookstore.xml");

            // marshalling
            jaxbMarshaller.marshal(bs, f);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // unmarshalling
            Bookstore o = (Bookstore) jaxbUnmarshaller.unmarshal(f);

            System.out.println(o.toString());



        }catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}




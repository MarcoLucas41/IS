package org.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import org.apache.commons.lang3.RandomStringUtils;
import org.configuration.Configuration;
import org.eclipse.persistence.jaxb.MarshallerProperties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;


import org.models.Author;
import org.models.Book;
import org.models.Bookstore;


import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import java.util.concurrent.ThreadLocalRandom;

// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive



public class App
{
    public static LocalDate createRandomDate() {
        int day = 1 + (int) Math.round(Math.random() * (28 - 1));
        int month = 1 + (int) Math.round(Math.random() * (12 - 1));
        int year = 1980 + (int) Math.round(Math.random() * (2024 - 1980));
        return LocalDate.of(year, month, day);
    }



    public static void main(String[] args) throws RemoteException
    {
        JAXBContext jaxbContext;
        ObjectMapper mapper;
        File JSONFILE,XMLFILE;
        try
        {
            // setting up jaxb context
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                    .createContext(new Class[]{Bookstore.class}, null);

            Marshaller jaxbXMLMarshaller = jaxbContext.createMarshaller();
            Unmarshaller jaxbXMLUnmarshaller = jaxbContext.createUnmarshaller();

            jaxbXMLMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


            jaxbXMLMarshaller .setProperty("org.glassfish.jaxb.xmlHeaders",
                        "<?xml-stylesheet type='text/xsl' href='test.xsl' ?>");
            jaxbXMLMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/xml");
            //jaxbXMLMarshaller.setProperty("org.glassfish.jaxb.namespacePrefixMapper", new DefaultNamespacePrefixMapper());


            //setting up object mapper
            mapper = new ObjectMapper();


            XMLFILE = new File("bookstore_app.xml");
            JSONFILE = new File("bookstore_app.json");

            ArrayList<Double> xmlvalues = new ArrayList<Double>();
            ArrayList<Double> jsonvalues = new ArrayList<Double>();

            // continuously creating, marshalling and unmarshalling
            for(int j = 0; j < Configuration.NUM_TESTS; j++)
            {
                // creating java object
                Bookstore bs = new Bookstore();
                ArrayList<Book> bklist = new ArrayList<>();

                for(int i = 0; i < Configuration.NUM_ELEMENTS; i++)
                {
                    Book bk = new Book();

                    Author au = new Author();

                    au.setId(Integer.toString(i));
                    au.setName(getTitle());
                    au.setAge(1 + (int) Math.round(Math.random() * (100 - 1)));
                    au.setDate_of_birth(new GregorianCalendar(1998, Calendar.FEBRUARY, 11).getTime());

                    bk.setId(Integer.toString(i));
                    bk.setTitle(getTitle());
                    //bk.setCreator(getTitle());
                    bk.setNum_pages(1 + (int) Math.round(Math.random() * (100 - 1)));
                    bk.setAuthor(au);
                    bk.setDate_of_publication(new GregorianCalendar(2014, Calendar.MARCH, 2).getTime());
                    bklist.add(bk);
                }

                bs.setBooks(bklist);


                double xmlstart = System.currentTimeMillis();
                jaxbXMLMarshaller.marshal(bs, XMLFILE);
                Bookstore XMLBS = (Bookstore) jaxbXMLUnmarshaller.unmarshal(XMLFILE);
                double xmlfinish= System.currentTimeMillis();
                double xmlduration = xmlfinish - xmlstart;

                double jsonstart = System.currentTimeMillis();
                mapper.writeValue(JSONFILE, bs);
                Bookstore JSONBS = mapper.readValue(JSONFILE, Bookstore.class);
                double jsonfinish = System.currentTimeMillis();
                double jsonduration = jsonfinish - jsonstart;

                xmlvalues.add(xmlduration);
                jsonvalues.add(jsonduration);



                //System.out.println("XML: "+xmlduration+" ms");
                //System.out.println("JSON: "+jsonduration+" ms");


                bklist.clear();
                //System.out.println(JSONBS);

            }
            double xmlsum = 0;
            double jsonsum = 0;
            for(int p = 0; p < xmlvalues.size();p++)
            {
                xmlsum += xmlvalues.get(p);
                jsonsum += jsonvalues.get(p);
            }
            double xmlavg = xmlsum/Configuration.NUM_TESTS;
            double jsonavg = jsonsum/Configuration.NUM_TESTS;

            System.out.println("-------------\nXML avg: "+xmlavg*1000+" microsec");
            System.out.println("JSON avg: "+jsonavg*1000+" microsec");






        }catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }



    private static String getTitle()
    {

        return RandomStringUtils.random(30, true, false);
        //return "Os Maias";
    }



}




package org.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

//@XmlRootElement(name="bookstore",namespace = "http://www.dei.uc.pt/EAI")
@XmlRootElement(name="bookstore")
// order of the fields in XML
@XmlAccessorType(XmlAccessType.FIELD)
public class Bookstore {
    //@XmlElement(name = "book",namespace = "http://www.dei.uc.pt/EAI")
    @XmlElement(name = "book")
    private ArrayList<Book> books;

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Bookstore{" +
                "books=" + books +
                '}';
    }
}
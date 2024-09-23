package org.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

@XmlRootElement(name="bookstore",namespace = "http://www.dei.uc.pt/EAI")

// order of the fields in XML
@XmlAccessorType(XmlAccessType.FIELD)
public class Bookstore {

    private ArrayList<Book> books;

    @XmlElement(name = "book")
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
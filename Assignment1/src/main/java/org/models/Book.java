package org.models;

import jakarta.xml.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class Book
{
    @XmlAttribute
    String id;
    @XmlElement
    String title;



    //@XmlElement
    //String creator;
    @XmlElement
    int num_pages;

    @XmlElement
    Date date_of_publication;
    @XmlElement
    Author author;







    public Author getAuthor() {
        return author;
    }


    public void setAuthor(Author author) {
        this.author = author;
    }

    @XmlTransient
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlTransient
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public int getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(int num_pages) {
        this.num_pages = num_pages;
    }




    public Date getDate_of_publication() {
        return date_of_publication;
    }

    public void setDate_of_publication(Date date_of_publication) {
        this.date_of_publication = date_of_publication;
    }

    /*
    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", num_pages=" + num_pages +
                ", author=" + author +
                '}';
    }*/
}
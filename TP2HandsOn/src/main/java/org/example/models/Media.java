package org.example.models;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Media{

    @Id
    private Long id;
    private String title;
    private LocalDate release_date;
    private int avg_rating;
    private String type;

    public Media(String title, LocalDate release_date, String type) {
        this.title = title;
        this.release_date = release_date;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", release_date=" + release_date +
                ", avg_rating=" + avg_rating +
                ", type='" + type + '\'' +
                '}';
    }
}

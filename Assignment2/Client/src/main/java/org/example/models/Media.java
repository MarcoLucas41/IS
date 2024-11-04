package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Media {
    private Long id;
    private String title;
    private LocalDate release_date;
    private float avg_rating;
    private String type;
    public Media(String title, LocalDate release_date) {
        this.title = title;
        this.release_date = release_date;
    }
}

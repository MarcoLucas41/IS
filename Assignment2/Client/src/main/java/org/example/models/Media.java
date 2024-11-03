package org.example.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class Media {
    private Long id;
    private String title;
    private LocalDate release_date;
    private float avg_rating;
    private String type;
}

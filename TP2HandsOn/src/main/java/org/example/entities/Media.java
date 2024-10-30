package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media{

    @Id
    private Long id;
    private String title;
    private LocalDate release_date;
    private int avg_rating;
    private String type;

    public List<Long> getConsumerIds() {
        return consumerIds;
    }

    // No relationship annotations needed here
    @Builder.Default
    private List<Long> consumerIds = new ArrayList<>(); // Store related Consumer IDs



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

package org.example.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Table(name = "media")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Media
{
    @Id
    private Long id;
    private String title;
    private LocalDate release_date;
    private float avg_rating = 0;
    private String type;

    public Media(String title, LocalDate release_date,String type) {
        this.title = title;
        this.release_date = release_date;
        this.type = type;
    }
}

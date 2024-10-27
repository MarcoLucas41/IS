package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media
{

    @Id
    private Long id;
    private String title;
    private LocalDate release_date;
    private int avg_rating;
    private String type;

}

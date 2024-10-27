package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media
{

    @Id
    private Long id;
    private String title;
    private Date release_date;
    private int avg_rating;
    private String type;

}

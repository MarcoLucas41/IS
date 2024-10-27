package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Media
{

    private String id;
    private String title;
    private Date release_date;
    private int avg_rating;
    private String type;

}

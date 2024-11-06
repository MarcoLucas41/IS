package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.Consumer;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConsumerWithMedia
{
    private Long consumerId;
    private String name;
    private int age;
    private String gender;
    private List<String> mediaTitles;// Store related Media IDs

    public void addMediaTitle(String media)
    {
        mediaTitles.add(media);
    }

}

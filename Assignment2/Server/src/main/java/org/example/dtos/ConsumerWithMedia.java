package org.example.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConsumerWithMedia
{
    private Long id;
    private String name;
    private int age;
    private String gender;
    private List<Long> media_ids;
    private List<String> mediaTitles;// Store related Media IDs
}

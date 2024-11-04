package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Relationship {
    private Long id;
    private Long consumerId;
    private Long mediaId;
    private float rating;
}

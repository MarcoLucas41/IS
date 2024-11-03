package org.example.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public class Relationship {
    private Long id;
    private Long consumerId;
    private Long mediaId;
    private float rating;
}

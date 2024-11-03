package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("consumer_media")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Relationship
{
    @Id
    private Long id;

    @Column("consumer_id")
    private Long consumerId;

    @Column("media_id")
    private Long mediaId;

    @Column("rating")
    private float rating;

}

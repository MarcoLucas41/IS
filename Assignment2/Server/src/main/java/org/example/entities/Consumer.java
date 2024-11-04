package org.example.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table(name = "consumer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
    @Id
    private Long id;
    private String name;
    private int age;
    private String gender;

}

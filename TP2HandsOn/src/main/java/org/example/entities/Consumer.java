package org.example.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumer {

    @Id
    private Long id;
    private String name;
    private int age;
    private String gender;

    @Builder.Default
    private List<Long> favoriteMediaIds = new ArrayList<>(); // Store related Media IDs

    public void addMedia(Long mediaId) {
        favoriteMediaIds.add(mediaId);
    }

    public Consumer(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}

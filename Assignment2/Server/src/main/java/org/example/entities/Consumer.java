package org.example.entities;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


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

    public Consumer(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

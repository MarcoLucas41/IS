package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.Consumer;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MediaUsersInfo
{
    private String mediaName;
    private int userCount;
    private List<Consumer> consumers = new ArrayList<>();

    public void addConsumer(Consumer consumer) {
        // Insert consumer in sorted order (descending by age)
        int index = 0;
        while (index < consumers.size() && consumers.get(index).getAge() > consumer.getAge()) {
            index++;
        }
        consumers.add(index, consumer);
        userCount++;
    }

    // Constructors, getters, and setters omitted for brevity
}

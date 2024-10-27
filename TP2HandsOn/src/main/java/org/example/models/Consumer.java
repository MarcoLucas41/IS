package org.example.models;

import org.springframework.data.annotation.Id;

public class Consumer {

    @Id
    private Long id;
    private String name;
    private int age;
    private String gender;

}

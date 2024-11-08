package org.example;



import lombok.Data;
import java.util.List;
@Data
public class Stats
{
    private final double average;
    private final List<Double> standardDeviations;
}
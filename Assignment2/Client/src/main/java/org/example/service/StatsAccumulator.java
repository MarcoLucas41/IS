package org.example.service;

import org.example.Stats;

import java.util.ArrayList;
import java.util.List;

public class StatsAccumulator {
    private double sum = 0.0;
    private double sumOfSquares = 0.0;

    private final List<Float> avgRatings = new ArrayList<>();
    private long count = 0;

    // Accumulate each rating
    public StatsAccumulator accumulate(float rating)
    {
        count++;
        sum += rating;
        sumOfSquares += rating * rating;
        avgRatings.add(rating);
        return this;
    }

    // Calculate final average and standard deviation
    public Stats calculateStats()
    {
        if (count == 0) {
            return new Stats(0.0, null); // Handle case with no ratings
        }

        double average = sum / count;
        double sumOfSquares = 0;
        double variance = 0;
        List<Double> standardDeviations = new ArrayList<>();

        for(float r: avgRatings)
        {
            sumOfSquares = r * r;
            variance = (sumOfSquares / count) - (average * average);
            standardDeviations.add(variance);
        }
        return new Stats(average, standardDeviations);
    }
}
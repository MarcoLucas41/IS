package org.example.service;

import org.example.Stats;

public class StatsAccumulator {
    private double sum = 0.0;
    private double sumOfSquares = 0.0;
    private long count = 0;

    // Accumulate each rating
    public StatsAccumulator accumulate(double rating)
    {
        count++;
        sum += rating;
        sumOfSquares += rating * rating;
        return this;
    }

    // Calculate final average and standard deviation
    public Stats calculateStats()
    {
        if (count == 0) {
            return new Stats(0.0, 0.0); // Handle case with no ratings
        }

        double average = sum / count;
        double variance = (sumOfSquares / count) - (average * average);
        double standardDeviation = Math.sqrt(variance);

        return new Stats(average, standardDeviation);
    }
}
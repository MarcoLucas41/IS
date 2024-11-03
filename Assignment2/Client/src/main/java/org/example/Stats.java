package org.example;

public class Stats
{
    private final double average;
    private final double standardDeviation;

    public Stats(double average, double standardDeviation) {
        this.average = average;
        this.standardDeviation = standardDeviation;
    }

    public double getAverage() {
        return average;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

}
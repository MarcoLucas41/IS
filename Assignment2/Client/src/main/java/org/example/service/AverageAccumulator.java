package org.example.service;

public class AverageAccumulator {
    private long totalUserCount = 0; // Sum of users per media
    private long mediaItemCount = 0; // Count of distinct media items

    // Accumulate user count and media count
    public AverageAccumulator accumulate(long userCountForMedia) {
        totalUserCount += userCountForMedia;
        mediaItemCount++;
        return this;
    }

    // Calculate average users per media
    public double calculateAverage() {
        if (mediaItemCount == 0) {
            return 0.0; // Handle case with no media items
        }
        return (double) totalUserCount / mediaItemCount;
    }
}
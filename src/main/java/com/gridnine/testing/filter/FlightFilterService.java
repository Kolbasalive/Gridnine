package com.gridnine.testing.filter;

import com.gridnine.testing.domain.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A service class providing predefined flight filters.
 */
public class FlightFilterService {
    private static final LocalDateTime DATE_NOW = LocalDateTime.now();

    private FlightFilterService() {}

    public static final FlightFilter DEPARTURE_BEFORE_NOW = flights -> flights.stream()
            .filter(
                    flight -> flight.getSegments()
                            .stream().anyMatch(
                                    segment -> segment.getDepartureDate()
                                            .isAfter(DATE_NOW)
                            )
            ).toList();

    public static final FlightFilter ARRIVAL_BEFORE_DEPARTURE = flights -> flights.stream()
            .filter(
                    flight -> flight.getSegments()
                            .stream().noneMatch(
                                    segment -> segment.getArrivalDate()
                                            .isBefore(segment.getDepartureDate())
                            )
            ).toList();

    public static final FlightFilter TOTAL_TIME_SPENT_GROUND_TWO_HOURS = flights -> flights.stream()
            .filter(flight -> {
                        List<Segment> segments = flight.getSegments();
                        Duration duration = Duration.ZERO;
                        for (int i = 0; i < segments.size() - 1; i++) {
                            var arrival = segments.get(i).getArrivalDate();
                            var departure = segments.get(i + 1).getDepartureDate();
                            duration = duration.plus(Duration.between(arrival, departure));
                        }
                        return duration.compareTo(Duration.ofHours(2)) <= 0;
                    }
            ).toList();

}
package com.gridnine.testing.filter;

import com.gridnine.testing.domain.Flight;

import java.util.List;

/**
 * Functional interface representing a filter for flights.
 */
@FunctionalInterface
public interface FlightFilter {
    List<Flight> filter(List<Flight> flight);
}

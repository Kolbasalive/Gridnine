package com.gridnine.testing.filter;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.utils.FlightBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.of;

/**
 * Parameterized tests for verifying the predefined filters in {@link FlightFilterService}.
 */
class FlightFilterServiceTest {
    private final List<Flight> TEST_FLIGHT = FlightBuilder.createFlights();

    private static Stream<Arguments> filterProvider() {
        return Stream.of(
                of(
                        "Departure before now",
                        FlightFilterService.DEPARTURE_BEFORE_NOW,
                        Set.of(2, 8) // indexes of flights with departure in the past
                ),
                of(
                        "Arrival before departure",
                        FlightFilterService.ARRIVAL_BEFORE_DEPARTURE,
                        Set.of(3, 8) // indexes of flights with incorrect arrival dates
                ),
                of(
                        "Ground time > 2h",
                        FlightFilterService.TOTAL_TIME_SPENT_GROUND_TWO_HOURS,
                        Set.of(4, 5, 7) // indexes of flights with long layovers
                )
        );

    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("filterProvider")
    void testFilters(String description, FlightFilter filter, Set<Integer> excludedIndexes) {
        List<Flight> filtered = filter.filter(TEST_FLIGHT);

        for (int index : excludedIndexes) {
            assertFalse(filtered.contains(TEST_FLIGHT.get(index)),
                    "Flight at index " + index + " should have been excluded");
        }
    }
}
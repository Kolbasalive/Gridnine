package com.gridnine.testing;

import com.gridnine.testing.domain.Flight;
import com.gridnine.testing.filter.FlightFilterService;
import com.gridnine.testing.utils.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Все перелёты:");
        flights.forEach(System.out::println);

        System.out.println("\nФильтр 1: вылет до текущего момента:");
        FlightFilterService.DEPARTURE_BEFORE_NOW.filter(flights).forEach(System.out::println);

        System.out.println("\nФильтр 2: сегменты с прилётом до вылета:");
        FlightFilterService.ARRIVAL_BEFORE_DEPARTURE.filter(flights).forEach(System.out::println);

        System.out.println("\nФильтр 3: общее время на земле более двух часов:");
        FlightFilterService.TOTAL_TIME_SPENT_GROUND_TWO_HOURS.filter(flights).forEach(System.out::println);
    }
}
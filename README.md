# Transport System

A Java-based transport management system demonstrating OOP principles including inheritance, abstraction, and polymorphism.

## Structure

The system models a public transport network with three vehicle types: Bus, Taxi, and Train. Each vehicle operates on routes and serves passengers.

## Classes

- Vehicle (abstract): Base class with plate number, fuel level, and movement behavior
- Bus: Fixed-route vehicle with passenger capacity
- Taxi: On-demand vehicle with driver and availability status
- Train: Rail-based vehicle with schedule and multiple cars
- Passenger: Users who book trips on vehicles
- Route: Defines origin, destination, and distance
- TransportSystem: Main class demonstrating the system

## Features

- Vehicle inheritance hierarchy with polymorphic move() method
- Route management with distance tracking
- Passenger booking system
- Fuel management and refueling
- Taxi availability and booking logic
- Train scheduling and delay handling

## Running

Compile and run TransportSystem.java to see the demonstration of vehicles moving, passengers booking trips, and system operations.

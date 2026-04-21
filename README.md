# Transport System

A Java-based transport management system demonstrating OOP principles including inheritance, abstraction, polymorphism, and comprehensive exception handling.

## Structure

The system models a public transport network with three vehicle types: Bus, Taxi, and Train. Each vehicle operates on routes and serves passengers with real-world constraints and validations.

## Classes

- **Vehicle** (abstract): Base class with plate number, fuel level, consumption tracking, and movement behavior
- **Bus**: Fixed-route vehicle with passenger capacity management and boarding system
- **Taxi**: On-demand vehicle with driver, availability checking, and trip completion tracking
- **Train**: Rail-based vehicle with schedule, delay management, and multiple cars
- **Passenger**: Users with ID who book trips on vehicles
- **Route**: Defines origin, destination, and distance with validation
- **TransportSystem**: Main class demonstrating the system with exception handling

## Key Improvements

### Real-Life Logic
- **Taxi Availability**: Checks availability before booking to prevent double-booking
- **Fuel Consumption**: Vehicles consume fuel based on distance and type
- **Capacity Management**: Buses track passengers and prevent overcrowding
- **Fuel Validation**: Vehicles check fuel levels before moving
- **Trip Completion**: Taxis track current passengers and complete trips

### Exception Handling
Custom exceptions in `exceptions/` package:
- `VehicleNotAvailableException`: Prevents booking unavailable vehicles
- `InsufficientFuelException`: Ensures adequate fuel for operations
- `CapacityExceededException`: Prevents bus overcrowding
- `InvalidRouteException`: Validates route parameters

### Additional Features
- Passenger ID tracking for better identification
- Input validation throughout (null checks, empty strings, negative values)
- Detailed error messages for debugging
- Train delay reasons and resume functionality
- Available seats tracking for buses

## Running

```bash
# Compile all files
javac src/*.java src/exceptions/*.java

# Run the system
java -cp src TransportSystem
```

The demo showcases:
- Bus passenger boarding and capacity management
- Taxi availability checking and booking
- Train delay and resume operations
- Exception handling scenarios
- Fuel management and refueling

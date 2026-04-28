# Transport System

A Java-based transport management system demonstrating OOP principles including inheritance, abstraction, polymorphism, comprehensive exception handling, **Java Collections Framework**, and **File I/O for data persistence**.

## Structure

The system models a public transport network with three vehicle types: Bus, Taxi, and Train. Each vehicle operates on routes and serves passengers with real-world constraints and validations.

## Classes

- **Vehicle** (abstract): Base class with plate number, fuel level, consumption tracking, and movement behavior
- **Bus**: Fixed-route vehicle with **List<Passenger>** for passenger management
- **Taxi**: On-demand vehicle with driver, availability checking, and trip completion tracking
- **Train**: Rail-based vehicle with **List<String>** for ordered stops management
- **Passenger**: Users with ID who book trips on vehicles
- **Route**: Defines origin, destination, and distance with validation
- **TransportManager**: Manages vehicles using **Set** and **Map** collections
- **TransportSystem**: Main class demonstrating the system with all features

## Key Features

### File I/O (Input/Output) Operations
**Demonstrates data persistence through reading and writing to files:**

1. **Writing Data (Output Operations)**
   - `saveVehicles()` - Write vehicle data to file
   - `saveRoutes()` - Write route data to file
   - `savePassengers()` - Write passenger data to file
   - `appendLog()` - Append system logs
   - `exportSystemReport()` - Generate comprehensive reports

2. **Reading Data (Input Operations)**
   - `loadVehicles()` - Read vehicle data from file
   - `loadRoutes()` - Read route data from file
   - `loadPassengers()` - Read passenger data from file
   - `readLogs()` - Retrieve system logs

3. **File I/O Features**
   - Uses `BufferedReader` and `BufferedWriter` for efficient I/O
   - Proper exception handling with try-catch blocks
   - Data persistence across program restarts
   - Human-readable text file format
   - Automatic directory creation

### Java Collections Framework
**Demonstrates real-world relationships using appropriate collection types:**

1. **List<Passenger> in Bus**
   - Relationship: One-to-Many (One Bus → Many Passengers)
   - Justification: Order matters for boarding sequence, allows indexed access
   - Operations: add, remove, get, contains, display

2. **Set<Vehicle> in TransportManager**
   - Relationship: Unique Collection (No duplicate vehicles)
   - Justification: Each vehicle must be unique based on plate number
   - Operations: add, remove, contains, size

3. **Map<String, List<Taxi>> in TransportManager**
   - Relationship: Key-Value with One-to-Many (Zone → Multiple Taxis)
   - Justification: Fast lookup of taxis by zone/area
   - Operations: put, get, keySet, values

4. **Map<String, Route> in TransportManager**
   - Relationship: Key-Value (Route ID → Route Object)
   - Justification: O(1) lookup for routes by unique identifier
   - Operations: put, get, remove, containsKey

5. **List<String> in Train**
   - Relationship: One-to-Many (One Train → Many Stops)
   - Justification: Maintains ordered sequence of stops
   - Operations: add, remove, get (for next stop)

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

### Using IntelliJ IDEA (Recommended)
1. Open the project in IntelliJ IDEA
2. Right-click on `src/TransportSystemWithFileIO.java` (for File I/O demo)
3. Or right-click on `src/TransportSystem.java` (for Collections demo)
4. Select "Run 'TransportSystemWithFileIO.main()'" or "Run 'TransportSystem.main()'"

### Using Command Line
```bash
# Compile all files
javac src/*.java src/exceptions/*.java src/io/*.java

# Run Collections Framework demo
java -cp src TransportSystem

# Run File I/O demonstration
java -cp src TransportSystemWithFileIO
```

The demos showcase:
- **File I/O Operations**: Writing and reading data from files
- **Data Persistence**: Saving and loading system state
- **Collections Framework**: List, Set, and Map operations
- **Bus Operations**: Passenger management with List collection
- **Taxi Management**: Zone-based lookup with Map collection
- **Train Operations**: Ordered stops with List collection
- **Vehicle Registry**: Unique vehicles with Set collection
- **Route Management**: Fast lookup with Map collection
- **Exception Handling**: Comprehensive error scenarios
- **Real-life Logic**: Availability checks, fuel management, capacity limits

## File I/O Data Files

After running the File I/O demo, check the `data/` directory:
- `vehicles.txt` - Vehicle registration data
- `routes.txt` - Route information
- `passengers.txt` - Passenger records
- `system.log` - System event logs
- `system_report.txt` - Exported system reports

## Collections Summary

| Collection Type | Used In | Relationship | Justification |
|----------------|---------|--------------|---------------|
| `List<Passenger>` | Bus | One-to-Many | Ordered passengers, boarding sequence |
| `Set<Vehicle>` | TransportManager | Unique Collection | No duplicate vehicles |
| `Map<String, List<Taxi>>` | TransportManager | Key-Value + One-to-Many | Zone-based taxi lookup |
| `Map<String, Route>` | TransportManager | Key-Value | Fast route lookup by ID |
| `List<String>` | Train | One-to-Many | Ordered sequence of stops |

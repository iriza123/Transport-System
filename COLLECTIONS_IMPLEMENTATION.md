# Java Collections Framework Implementation

## Overview
This document explains how the Java Collections Framework was applied to model real-world relationships in the Transport System.

## Collections Implemented

### 1. List<Passenger> in Bus Class
**File:** `src/Bus.java`

**Relationship Type:** One-to-Many (One Bus → Many Passengers)

**Justification:**
- A bus can carry multiple passengers
- Order matters for boarding sequence and seat assignment
- Need indexed access for operations like removing specific passengers
- ArrayList provides efficient random access and dynamic sizing

**Operations Demonstrated:**
```java
// Adding elements
bus.addPassenger(passenger);

// Removing elements
bus.removePassenger(passenger);

// Retrieving elements
List<Passenger> passengers = bus.getPassengers();
int count = bus.getPassengerCount();

// Checking existence
boolean hasPassenger = bus.hasPassenger(passenger);
```

---

### 2. Set<Vehicle> in TransportManager Class
**File:** `src/TransportManager.java`

**Relationship Type:** Unique Collection (No Duplicate Vehicles)

**Justification:**
- Each vehicle must be unique in the system (based on plate number)
- No duplicate registrations allowed
- Order doesn't matter for vehicle registration
- HashSet provides O(1) lookup performance
- Implemented equals() and hashCode() in Vehicle class for proper Set behavior

**Operations Demonstrated:**
```java
// Adding elements
manager.registerVehicle(vehicle);

// Removing elements
manager.unregisterVehicle(vehicle);

// Retrieving elements
Set<Vehicle> allVehicles = manager.getAllVehicles();
int totalCount = manager.getTotalVehicleCount();

// Checking existence
boolean isRegistered = manager.isVehicleRegistered(vehicle);
```

---

### 3. Map<String, List<Taxi>> in TransportManager Class
**File:** `src/TransportManager.java`

**Relationship Type:** Key-Value with One-to-Many (Zone → Multiple Taxis)

**Justification:**
- Map allows quick lookup of taxis by zone/area
- Key: Zone name (e.g., "Downtown", "Airport")
- Value: List of taxis operating in that zone
- Efficient for finding available taxis in specific locations
- HashMap provides O(1) average lookup time

**Operations Demonstrated:**
```java
// Adding elements
manager.addTaxiToZone(taxi, "Downtown");

// Retrieving elements
List<Taxi> taxisInZone = manager.getTaxisInZone("Downtown");
List<Taxi> availableTaxis = manager.getAvailableTaxisInZone("Downtown");

// Getting all zones
Set<String> zones = manager.getAllZones();
```

---

### 4. Map<String, Route> in TransportManager Class
**File:** `src/TransportManager.java`

**Relationship Type:** Key-Value (Route ID → Route Object)

**Justification:**
- Map provides O(1) lookup by route ID
- Each route has a unique identifier
- Fast retrieval when booking trips
- HashMap for efficient key-based access

**Operations Demonstrated:**
```java
// Adding elements
manager.addRoute("R001", route);

// Retrieving elements
Route route = manager.getRoute("R001");

// Checking existence
boolean hasRoute = manager.hasRoute("R001");

// Removing elements
Route removed = manager.removeRoute("R001");

// Getting all route IDs
Set<String> routeIds = manager.getAllRouteIds();
```

---

### 5. List<String> in Train Class
**File:** `src/Train.java`

**Relationship Type:** One-to-Many (One Train → Many Stops)

**Justification:**
- List maintains the order of stops (sequence matters for train routes)
- Trains visit stops in a specific order
- Allows duplicate stops (train might pass through same station on return)
- Indexed access for "next stop" operations
- ArrayList for ordered sequence management

**Operations Demonstrated:**
```java
// Adding elements
train.addStop("Kigali Central");

// Retrieving elements
List<String> stops = train.getStops();
String nextStop = train.getNextStop(currentIndex);

// Removing elements
train.removeStop("Kigali Central");

// Display
train.displayStops();
```

---

## Design Decisions

### Why List vs Set vs Map?

| Collection | When to Use | Example in Project |
|------------|-------------|-------------------|
| **List** | Order matters, duplicates allowed, indexed access needed | Bus passengers, Train stops |
| **Set** | Uniqueness required, no duplicates, order doesn't matter | Vehicle registry |
| **Map** | Key-value lookup, fast retrieval by key | Taxis by zone, Routes by ID |

### Implementation Details

1. **Encapsulation:** All collections are private with controlled access through public methods
2. **Defensive Copying:** Methods return copies of collections to protect internal state
3. **Validation:** All operations validate inputs (null checks, empty checks)
4. **Type Safety:** Generic types ensure compile-time type checking

### OOP Principles Applied

- **Abstraction:** Collections hide implementation details
- **Encapsulation:** Private collections with public interfaces
- **Polymorphism:** Using Collection interfaces (List, Set, Map)
- **Single Responsibility:** Each class manages its own collections

## Real-World Scenarios Modeled

1. **Bus Passenger Management:** Passengers board and alight in sequence
2. **Taxi Zone Dispatch:** Find available taxis in specific areas
3. **Train Route Planning:** Maintain ordered sequence of stops
4. **Vehicle Registry:** Prevent duplicate vehicle registrations
5. **Route Lookup:** Quick access to route information by ID

## Testing the Implementation

Run the system to see all collections in action:
```bash
javac src/*.java src/exceptions/*.java
java -cp src TransportSystem
```

The demo demonstrates:
- Adding, removing, and retrieving elements from all collections
- Preventing duplicates in Set
- Zone-based taxi lookup with Map
- Ordered operations with List
- Real-world transportation scenarios

## Conclusion

This implementation demonstrates proper use of Java Collections Framework to model real-world relationships:
- **List** for ordered, one-to-many relationships
- **Set** for unique collections
- **Map** for key-value lookups and complex relationships

Each collection type was chosen based on the specific requirements of the relationship it models, ensuring efficient and maintainable code.

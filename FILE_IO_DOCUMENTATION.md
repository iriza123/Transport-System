# File I/O Implementation Documentation

## Overview
This document explains the File I/O (Input/Output) implementation in the Transport System, demonstrating data persistence through reading and writing to files.

## File I/O Operations Implemented

### 1. Writing Data to Files (Output Operations)

#### **DataPersistence.saveVehicles()**
- **Purpose**: Write vehicle data to file
- **File**: `data/vehicles.txt`
- **Method**: Uses `BufferedWriter` for efficient writing
- **Format**: Plain text with headers and vehicle information
```java
BufferedWriter writer = new BufferedWriter(new FileWriter(VEHICLES_FILE))
```

#### **DataPersistence.saveRoutes()**
- **Purpose**: Write route data to file
- **File**: `data/routes.txt`
- **Method**: Uses `BufferedWriter`
- **Format**: Key-value pairs separated by pipe (|)
```java
writer.write(routeId + "|" + routeData);
```

#### **DataPersistence.savePassengers()**
- **Purpose**: Write passenger data to file
- **File**: `data/passengers.txt`
- **Method**: Uses `BufferedWriter`
- **Format**: ID|Name format

#### **DataPersistence.appendLog()**
- **Purpose**: Append log entries to system log
- **File**: `data/system.log`
- **Method**: Uses `BufferedWriter` with append mode
- **Format**: Timestamped log entries
```java
BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))
```

#### **TransportManager.exportSystemReport()**
- **Purpose**: Export comprehensive system report
- **File**: `data/system_report.txt`
- **Method**: Uses `BufferedWriter`
- **Format**: Formatted report with all system data

---

### 2. Reading Data from Files (Input Operations)

#### **DataPersistence.loadVehicles()**
- **Purpose**: Read vehicle data from file
- **File**: `data/vehicles.txt`
- **Method**: Uses `BufferedReader` for efficient reading
- **Returns**: List of vehicle data strings
```java
BufferedReader reader = new BufferedReader(new FileReader(file))
```

#### **DataPersistence.loadRoutes()**
- **Purpose**: Read route data from file
- **File**: `data/routes.txt`
- **Method**: Uses `BufferedReader`
- **Returns**: Map of route ID to route data

#### **DataPersistence.loadPassengers()**
- **Purpose**: Read passenger data from file
- **File**: `data/passengers.txt`
- **Method**: Uses `BufferedReader`
- **Returns**: List of passenger data strings

#### **DataPersistence.readLogs()**
- **Purpose**: Read all system logs
- **File**: `data/system.log`
- **Method**: Uses `BufferedReader`
- **Returns**: List of log entries

---

## File Structure

### Data Directory Structure
```
data/
├── vehicles.txt          # Vehicle registration data
├── routes.txt            # Route information
├── passengers.txt        # Passenger records
├── system.log           # System event logs
└── system_report.txt    # Exported system reports
```

### File Formats

#### vehicles.txt
```
=== TRANSPORT SYSTEM - VEHICLE DATA ===
Generated: [timestamp]
Total Vehicles: 5
==========================================

RAB123A|Bus|50
RAC456B|Taxi|40
RAD789C|Train|100
```

#### routes.txt
```
=== TRANSPORT SYSTEM - ROUTE DATA ===
Generated: [timestamp]
Total Routes: 3
======================================

R001|Kigali|Huye|130.0
R002|Kigali|Musanze|90.0
```

#### passengers.txt
```
=== TRANSPORT SYSTEM - PASSENGER DATA ===
Generated: [timestamp]
Total Passengers: 3
=========================================

P001|John
P002|Alice
P003|Bob
```

#### system.log
```
[Tue Apr 22 10:30:45 2026] System data saved successfully
[Tue Apr 22 10:30:46 2026] Taxi RAC456B booked by Bob
[Tue Apr 22 10:30:47 2026] Bus RAB123A has 2 passengers
```

---

## Key Features

### 1. **Efficient I/O Operations**
- Uses `BufferedReader` and `BufferedWriter` for performance
- Reduces disk I/O operations through buffering

### 2. **Exception Handling**
- All I/O operations wrapped in try-catch blocks
- Proper resource management with try-with-resources
- Graceful error messages

### 3. **Data Persistence**
- Data survives program restarts
- Can reload previous state
- Maintains data integrity

### 4. **Serialization Support**
- Classes implement `toFileString()` for writing
- Classes implement `fromFileString()` for reading
- Consistent format across all data types

---

## Usage Examples

### Writing Data
```java
// Save all system data
manager.saveAllData();

// Save specific passenger data
List<String> passengerData = new ArrayList<>();
passengerData.add(passenger.toFileString());
DataPersistence.savePassengers(passengerData);

// Export system report
manager.exportSystemReport("report.txt");

// Append to log
DataPersistence.appendLog("Operation completed");
```

### Reading Data
```java
// Load all system data
manager.loadAllData();

// Load specific data
List<String> vehicles = DataPersistence.loadVehicles();
Map<String, String> routes = DataPersistence.loadRoutes();
List<String> passengers = DataPersistence.loadPassengers();

// Read logs
List<String> logs = DataPersistence.readLogs();
manager.displayLogs();
```

---

## Implementation Details

### BufferedReader/BufferedWriter
- **Why**: More efficient than FileReader/FileWriter
- **Benefit**: Reduces number of I/O operations
- **Usage**: Wraps FileReader/FileWriter for buffering

### Try-with-Resources
```java
try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
    // Automatic resource management
    // File is closed automatically
}
```

### File Format Design
- **Pipe-separated values**: Easy to parse, human-readable
- **Headers**: Provide context and metadata
- **Timestamps**: Track when data was saved
- **Counts**: Validate data integrity

---

## Error Handling

### IOException Handling
```java
try {
    DataPersistence.saveVehicles(data);
} catch (IOException e) {
    System.err.println("Error saving data: " + e.getMessage());
    e.printStackTrace();
}
```

### File Not Found
- Methods check if file exists before reading
- Return empty collections if file doesn't exist
- No crashes from missing files

### Invalid Data Format
- Validation when parsing file data
- Skip invalid entries with error messages
- Continue processing valid data

---

## Benefits of File I/O Implementation

1. **Data Persistence**: Data survives program restarts
2. **Backup**: Easy to backup by copying data directory
3. **Portability**: Text files can be moved between systems
4. **Debugging**: Human-readable format for troubleshooting
5. **Audit Trail**: System logs track all operations
6. **Reporting**: Export reports for analysis

---

## Testing the Implementation

### Run the File I/O Demo
```bash
# Compile
javac src/*.java src/exceptions/*.java src/io/*.java

# Run File I/O demonstration
java -cp src TransportSystemWithFileIO

# Check generated files
ls data/
cat data/vehicles.txt
cat data/routes.txt
cat data/passengers.txt
cat data/system.log
```

### Verify Data Persistence
1. Run the program once to save data
2. Close the program
3. Run again and load data
4. Verify data was restored correctly

---

## Conclusion

This File I/O implementation demonstrates:
- ✅ Writing data to files (Output)
- ✅ Reading data from files (Input)
- ✅ Efficient buffered I/O operations
- ✅ Proper exception handling
- ✅ Data persistence and retrieval
- ✅ Real-world file management
- ✅ System logging and reporting

The implementation ensures data integrity, provides efficient I/O operations, and maintains a clean, maintainable codebase.

import exceptions.*;
import io.DataPersistence;
import java.util.*;
import java.io.IOException;

/**
 * TransportManager - Manages all vehicles and routes in the transport system
 * Includes data persistence through File I/O operations
 */
public class TransportManager {
    
    /**
     * Set of registered vehicles (ensures uniqueness)
     */
    private Set<Vehicle> registeredVehicles;
    
    /**
     * Map of taxis organized by zone
     * Key: Zone name, Value: List of taxis in that zone
     */
    private Map<String, List<Taxi>> taxisByZone;
    
    /**
     * Map of routes by route ID
     * Key: Route ID, Value: Route object
     */
    private Map<String, Route> routes;

    public TransportManager() {
        this.registeredVehicles = new HashSet<>();
        this.taxisByZone = new HashMap<>();
        this.routes = new HashMap<>();
    }

    // ========== VEHICLE MANAGEMENT ==========
    
    public boolean registerVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        boolean added = registeredVehicles.add(vehicle);
        if (added) {
            System.out.println("Vehicle " + vehicle.getPlateNumber() + " registered successfully.");
            
            // If it's a taxi, add to zone mapping
            if (vehicle instanceof Taxi) {
                addTaxiToZone((Taxi) vehicle, "Central"); // Default zone
            }
        } else {
            System.out.println("Vehicle " + vehicle.getPlateNumber() + " is already registered.");
        }
        return added;
    }

    public boolean unregisterVehicle(Vehicle vehicle) {
        boolean removed = registeredVehicles.remove(vehicle);
        if (removed) {
            System.out.println("Vehicle " + vehicle.getPlateNumber() + " unregistered.");
            
            // Remove from taxi zones if applicable
            if (vehicle instanceof Taxi) {
                removeTaxiFromAllZones((Taxi) vehicle);
            }
        }
        return removed;
    }

    public Set<Vehicle> getAllVehicles() {
        return new HashSet<>(registeredVehicles);
    }

    public int getTotalVehicleCount() {
        return registeredVehicles.size();
    }

    public boolean isVehicleRegistered(Vehicle vehicle) {
        return registeredVehicles.contains(vehicle);
    }

    // ========== TAXI ZONE MANAGEMENT ==========
    
    public void addTaxiToZone(Taxi taxi, String zone) {
        if (taxi == null || zone == null || zone.trim().isEmpty()) {
            throw new IllegalArgumentException("Taxi and zone cannot be null");
        }
        
        // Get or create the list for this zone
        taxisByZone.putIfAbsent(zone, new ArrayList<>());
        List<Taxi> taxisInZone = taxisByZone.get(zone);
        
        if (!taxisInZone.contains(taxi)) {
            taxisInZone.add(taxi);
            System.out.println("Taxi " + taxi.getPlateNumber() + " added to zone: " + zone);
        }
    }

    public List<Taxi> getTaxisInZone(String zone) {
        List<Taxi> taxisInZone = taxisByZone.get(zone);
        return taxisInZone != null ? new ArrayList<>(taxisInZone) : new ArrayList<>();
    }

    public List<Taxi> getAvailableTaxisInZone(String zone) {
        List<Taxi> available = new ArrayList<>();
        List<Taxi> taxisInZone = getTaxisInZone(zone);
        
        for (Taxi taxi : taxisInZone) {
            if (taxi.isAvailable()) {
                available.add(taxi);
            }
        }
        return available;
    }

    private void removeTaxiFromAllZones(Taxi taxi) {
        for (List<Taxi> taxiList : taxisByZone.values()) {
            taxiList.remove(taxi);
        }
    }

    public Set<String> getAllZones() {
        return new HashSet<>(taxisByZone.keySet());
    }

    // ========== ROUTE MANAGEMENT ==========
    
    public void addRoute(String routeId, Route route) {
        if (routeId == null || route == null) {
            throw new IllegalArgumentException("Route ID and route cannot be null");
        }
        routes.put(routeId, route);
        System.out.println("Route " + routeId + " added: " + route.getOrigin() + " → " + route.getDestination());
    }

    public Route getRoute(String routeId) {
        return routes.get(routeId);
    }

    public boolean hasRoute(String routeId) {
        return routes.containsKey(routeId);
    }

    public Route removeRoute(String routeId) {
        Route removed = routes.remove(routeId);
        if (removed != null) {
            System.out.println("Route " + routeId + " removed.");
        }
        return removed;
    }

    public Set<String> getAllRouteIds() {
        return new HashSet<>(routes.keySet());
    }

    // ========== DISPLAY METHODS ==========
    
    public void displayAllVehicles() {
        System.out.println("\n===== REGISTERED VEHICLES (" + registeredVehicles.size() + ") =====");
        for (Vehicle vehicle : registeredVehicles) {
            vehicle.displayInfo();
        }
    }

    public void displayTaxisByZone() {
        System.out.println("\n===== TAXIS BY ZONE =====");
        for (Map.Entry<String, List<Taxi>> entry : taxisByZone.entrySet()) {
            System.out.println("Zone: " + entry.getKey() + " (" + entry.getValue().size() + " taxis)");
            for (Taxi taxi : entry.getValue()) {
                System.out.println("  - " + taxi.getPlateNumber() + " (Driver: " + taxi.getDriverName() + 
                                 ", Available: " + taxi.isAvailable() + ")");
            }
        }
    }

    public void displayAllRoutes() {
        System.out.println("\n===== AVAILABLE ROUTES (" + routes.size() + ") =====");
        for (Map.Entry<String, Route> entry : routes.entrySet()) {
            System.out.print("Route ID: " + entry.getKey() + " - ");
            entry.getValue().displayRoute();
        }
    }

    // ========== FILE I/O OPERATIONS ==========
    
    /**
     * Save all system data to files
     */
    public void saveAllData() {
        try {
            // Save vehicles
            List<String> vehicleData = new ArrayList<>();
            for (Vehicle vehicle : registeredVehicles) {
                String vehicleInfo = vehicle.getPlateNumber() + "|" + 
                                   vehicle.getClass().getSimpleName() + "|" + 
                                   vehicle.getFuelLevel();
                vehicleData.add(vehicleInfo);
            }
            DataPersistence.saveVehicles(vehicleData);
            
            // Save routes
            Map<String, String> routeData = new HashMap<>();
            for (Map.Entry<String, Route> entry : routes.entrySet()) {
                routeData.put(entry.getKey(), entry.getValue().toFileString());
            }
            DataPersistence.saveRoutes(routeData);
            
            // Log the save operation
            DataPersistence.appendLog("System data saved successfully");
            
            System.out.println("\n✓ All data saved successfully!");
            
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Load all system data from files
     */
    public void loadAllData() {
        try {
            System.out.println("\n===== LOADING DATA FROM FILES =====");
            
            // Load routes
            Map<String, String> routeData = DataPersistence.loadRoutes();
            for (Map.Entry<String, String> entry : routeData.entrySet()) {
                try {
                    Route route = Route.fromFileString(entry.getValue());
                    routes.put(entry.getKey(), route);
                } catch (Exception e) {
                    System.err.println("Error loading route: " + entry.getKey());
                }
            }
            
            // Load vehicles (basic info only - full reconstruction would need more data)
            List<String> vehicleData = DataPersistence.loadVehicles();
            System.out.println("Vehicle data loaded: " + vehicleData.size() + " entries");
            
            // Log the load operation
            DataPersistence.appendLog("System data loaded successfully");
            
            System.out.println("✓ Data loaded successfully!\n");
            
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
    
    /**
     * Export system report to file
     */
    public void exportSystemReport(String filename) {
        try {
            DataPersistence.initializeDataDirectory();
            java.io.BufferedWriter writer = new java.io.BufferedWriter(
                new java.io.FileWriter("data/" + filename)
            );
            
            writer.write("╔════════════════════════════════════════════════════════════╗\n");
            writer.write("║           TRANSPORT SYSTEM - FULL REPORT                  ║\n");
            writer.write("╚════════════════════════════════════════════════════════════╝\n\n");
            writer.write("Generated: " + new java.util.Date() + "\n\n");
            
            // Vehicles section
            writer.write("===== REGISTERED VEHICLES (" + registeredVehicles.size() + ") =====\n");
            for (Vehicle vehicle : registeredVehicles) {
                writer.write("- " + vehicle.getPlateNumber() + " (" + 
                           vehicle.getClass().getSimpleName() + ") - Fuel: " + 
                           vehicle.getFuelLevel() + "\n");
            }
            writer.write("\n");
            
            // Routes section
            writer.write("===== AVAILABLE ROUTES (" + routes.size() + ") =====\n");
            for (Map.Entry<String, Route> entry : routes.entrySet()) {
                writer.write("- " + entry.getKey() + ": " + entry.getValue().toString() + "\n");
            }
            writer.write("\n");
            
            // Taxi zones section
            writer.write("===== TAXI ZONES (" + taxisByZone.size() + ") =====\n");
            for (Map.Entry<String, List<Taxi>> entry : taxisByZone.entrySet()) {
                writer.write("- " + entry.getKey() + ": " + entry.getValue().size() + " taxis\n");
            }
            
            writer.close();
            System.out.println("✓ System report exported to: data/" + filename);
            
        } catch (IOException e) {
            System.err.println("Error exporting report: " + e.getMessage());
        }
    }
    
    /**
     * Display system logs
     */
    public void displayLogs() {
        try {
            List<String> logs = DataPersistence.readLogs();
            System.out.println("\n===== SYSTEM LOGS (" + logs.size() + " entries) =====");
            for (String log : logs) {
                System.out.println(log);
            }
        } catch (IOException e) {
            System.err.println("Error reading logs: " + e.getMessage());
        }
    }
}

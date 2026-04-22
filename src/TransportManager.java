import exceptions.*;
import java.util.*;

/**
 * TransportManager - Manages all vehicles and routes in the transport system
 * Demonstrates multiple collection types for different relationships
 */
public class TransportManager {
    
    /**
     * COLLECTION TYPE: Set<Vehicle>
     * RELATIONSHIP: Unique Collection (No duplicate vehicles)
     * JUSTIFICATION:
     * - Set ensures no duplicate vehicles (based on plate number)
     * - Each vehicle must be unique in the system
     * - Order doesn't matter for vehicle registration
     * - Fast lookup to check if vehicle exists
     */
    private Set<Vehicle> registeredVehicles;
    
    /**
     * COLLECTION TYPE: Map<String, List<Taxi>>
     * RELATIONSHIP: Key-Value with One-to-Many (Route → Multiple Taxis)
     * JUSTIFICATION:
     * - Map allows quick lookup of taxis by route/area
     * - Key: Area/Zone name (e.g., "Downtown", "Airport")
     * - Value: List of taxis operating in that area
     * - Efficient for finding available taxis in specific locations
     */
    private Map<String, List<Taxi>> taxisByZone;
    
    /**
     * COLLECTION TYPE: Map<String, Route>
     * RELATIONSHIP: Key-Value (Route ID → Route Object)
     * JUSTIFICATION:
     * - Map provides O(1) lookup by route ID
     * - Each route has a unique identifier
     * - Fast retrieval when booking trips
     */
    private Map<String, Route> routes;

    public TransportManager() {
        this.registeredVehicles = new HashSet<>(); // HashSet for fast lookup
        this.taxisByZone = new HashMap<>(); // HashMap for efficient zone-based lookup
        this.routes = new HashMap<>(); // HashMap for route management
    }

    // ========== VEHICLE MANAGEMENT (Set Operations) ==========
    
    // COLLECTION OPERATION: Adding to Set
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

    // COLLECTION OPERATION: Removing from Set
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

    // COLLECTION OPERATION: Retrieving from Set
    public Set<Vehicle> getAllVehicles() {
        return new HashSet<>(registeredVehicles); // Return copy
    }

    public int getTotalVehicleCount() {
        return registeredVehicles.size();
    }

    public boolean isVehicleRegistered(Vehicle vehicle) {
        return registeredVehicles.contains(vehicle);
    }

    // ========== TAXI ZONE MANAGEMENT (Map Operations) ==========
    
    // COLLECTION OPERATION: Adding to Map
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

    // COLLECTION OPERATION: Retrieving from Map
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

    // COLLECTION OPERATION: Removing from Map
    private void removeTaxiFromAllZones(Taxi taxi) {
        for (List<Taxi> taxiList : taxisByZone.values()) {
            taxiList.remove(taxi);
        }
    }

    public Set<String> getAllZones() {
        return new HashSet<>(taxisByZone.keySet());
    }

    // ========== ROUTE MANAGEMENT (Map Operations) ==========
    
    // COLLECTION OPERATION: Adding to Map
    public void addRoute(String routeId, Route route) {
        if (routeId == null || route == null) {
            throw new IllegalArgumentException("Route ID and route cannot be null");
        }
        routes.put(routeId, route);
        System.out.println("Route " + routeId + " added: " + route.getOrigin() + " → " + route.getDestination());
    }

    // COLLECTION OPERATION: Retrieving from Map
    public Route getRoute(String routeId) {
        return routes.get(routeId);
    }

    public boolean hasRoute(String routeId) {
        return routes.containsKey(routeId);
    }

    // COLLECTION OPERATION: Removing from Map
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
}

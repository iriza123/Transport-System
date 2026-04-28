import exceptions.*;
import io.DataPersistence;
import java.util.*;

public class TransportSystemWithFileIO {
    public static void main(String[] args) {
        try {
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.println("║   TRANSPORT SYSTEM - WITH FILE I/O DEMONSTRATION          ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝\n");

            // Create TransportManager
            TransportManager manager = new TransportManager();

            // ===== DEMONSTRATION 1: Creating and Saving Data =====
            System.out.println("===== STEP 1: CREATING SYSTEM DATA =====\n");

            // Create routes
            Route route1 = new Route("Kigali", "Huye", 130);
            Route route2 = new Route("Kigali", "Musanze", 90);
            Route route3 = new Route("Huye", "Butare", 15);

            manager.addRoute("R001", route1);
            manager.addRoute("R002", route2);
            manager.addRoute("R003", route3);

            // Create vehicles
            Bus bus1 = new Bus("RAB123A", 50, 30, route1);
            Bus bus2 = new Bus("RAB456B", 60, 25, route2);
            Train train = new Train("RAD789C", 100, "10:00 AM", 8);
            Taxi taxi1 = new Taxi("RAC456B", 40, "Eric", true);
            Taxi taxi2 = new Taxi("RAC789D", 35, "Marie", true);

            // Register vehicles
            manager.registerVehicle(bus1);
            manager.registerVehicle(bus2);
            manager.registerVehicle(train);
            manager.registerVehicle(taxi1);
            manager.registerVehicle(taxi2);

            // Add taxis to zones
            manager.addTaxiToZone(taxi1, "Downtown");
            manager.addTaxiToZone(taxi2, "Airport");

            // Create passengers
            Passenger p1 = new Passenger("John", "P001");
            Passenger p2 = new Passenger("Alice", "P002");
            Passenger p3 = new Passenger("Bob", "P003");

            // Add passengers to bus
            bus1.addPassenger(p1);
            bus1.addPassenger(p2);

            // Add stops to train
            train.addStop("Kigali Central");
            train.addStop("Rwamagana");
            train.addStop("Kayonza");

            // ===== DEMONSTRATION 2: FILE I/O - WRITING DATA =====
            System.out.println("\n\n===== STEP 2: SAVING DATA TO FILES =====\n");
            
            // Save all system data
            manager.saveAllData();
            
            // Save passenger data separately
            List<String> passengerData = new ArrayList<>();
            passengerData.add(p1.toFileString());
            passengerData.add(p2.toFileString());
            passengerData.add(p3.toFileString());
            DataPersistence.savePassengers(passengerData);
            
            // Export system report
            manager.exportSystemReport("system_report.txt");

            // ===== DEMONSTRATION 3: FILE I/O - READING DATA =====
            System.out.println("\n\n===== STEP 3: READING DATA FROM FILES =====\n");
            
            // Create a new manager to demonstrate loading
            TransportManager newManager = new TransportManager();
            newManager.loadAllData();
            
            // Load passengers
            List<String> loadedPassengerData = DataPersistence.loadPassengers();
            System.out.println("\nLoaded Passengers:");
            for (String passengerStr : loadedPassengerData) {
                try {
                    Passenger p = Passenger.fromFileString(passengerStr);
                    System.out.println("  - " + p.toString());
                } catch (Exception e) {
                    System.err.println("Error loading passenger: " + passengerStr);
                }
            }

            // ===== DEMONSTRATION 4: SYSTEM OPERATIONS =====
            System.out.println("\n\n===== STEP 4: PERFORMING OPERATIONS =====\n");
            
            // Display current system state
            manager.displayAllVehicles();
            manager.displayAllRoutes();
            manager.displayTaxisByZone();
            
            // Perform some operations
            System.out.println("\n--- Operations ---");
            bus1.displayPassengers();
            train.displayStops();
            
            // Book a taxi
            System.out.println("\nBooking taxi:");
            taxi1.acceptBooking(p3, route2);

            // ===== DEMONSTRATION 5: LOGGING =====
            System.out.println("\n\n===== STEP 5: SYSTEM LOGS =====");
            
            // Add some log entries
            DataPersistence.appendLog("Taxi " + taxi1.getPlateNumber() + " booked by " + p3.getName());
            DataPersistence.appendLog("Bus " + bus1.getPlateNumber() + " has " + bus1.getPassengerCount() + " passengers");
            DataPersistence.appendLog("Train " + train.getPlateNumber() + " has " + train.getStops().size() + " stops");
            
            // Display logs
            manager.displayLogs();

            // ===== DEMONSTRATION 6: FILE I/O OPERATIONS SUMMARY =====
            System.out.println("\n\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║              FILE I/O OPERATIONS SUMMARY                   ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║ 1. WRITING DATA TO FILES                                   ║");
            System.out.println("║    ✓ Vehicle data saved to: data/vehicles.txt             ║");
            System.out.println("║    ✓ Route data saved to: data/routes.txt                 ║");
            System.out.println("║    ✓ Passenger data saved to: data/passengers.txt         ║");
            System.out.println("║    ✓ System report exported to: data/system_report.txt    ║");
            System.out.println("║                                                            ║");
            System.out.println("║ 2. READING DATA FROM FILES                                 ║");
            System.out.println("║    ✓ Loaded vehicles from file                            ║");
            System.out.println("║    ✓ Loaded routes from file                              ║");
            System.out.println("║    ✓ Loaded passengers from file                          ║");
            System.out.println("║                                                            ║");
            System.out.println("║ 3. LOGGING OPERATIONS                                      ║");
            System.out.println("║    ✓ System events logged to: data/system.log             ║");
            System.out.println("║    ✓ Logs can be retrieved and displayed                  ║");
            System.out.println("║                                                            ║");
            System.out.println("║ 4. DATA PERSISTENCE                                        ║");
            System.out.println("║    ✓ Data survives program restarts                       ║");
            System.out.println("║    ✓ BufferedReader/Writer for efficient I/O              ║");
            System.out.println("║    ✓ Proper exception handling                            ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");

            System.out.println("\n\n✓ File I/O demonstration completed successfully!");
            System.out.println("Check the 'data/' directory for generated files.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

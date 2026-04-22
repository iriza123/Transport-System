import exceptions.InsufficientFuelException;
import java.util.*;

/**
 * Train class with collection for managing multiple stops
 */
class Train extends Vehicle {
    private String schedule;
    private int cars;
    private boolean delayed;
    
    /**
     * COLLECTION TYPE: List<String>
     * RELATIONSHIP: One-to-Many (One Train → Many Stops)
     * JUSTIFICATION:
     * - List maintains the order of stops (sequence matters for train routes)
     * - Trains visit stops in a specific order
     * - Allows duplicate stops (train might pass through same station on return)
     * - Indexed access for "next stop" operations
     */
    private List<String> stops;

    public Train(String plateNumber, int fuelLevel, String schedule, int cars) {
        super(plateNumber, fuelLevel);
        if (schedule == null || schedule.trim().isEmpty()) {
            throw new IllegalArgumentException("Schedule cannot be null or empty");
        }
        if (cars <= 0) {
            throw new IllegalArgumentException("Number of cars must be positive");
        }
        this.schedule = schedule;
        this.cars = cars;
        this.delayed = false;
        this.stops = new ArrayList<>(); // ArrayList for ordered stops
    }

    // COLLECTION OPERATION: Adding elements
    public void addStop(String stop) {
        if (stop == null || stop.trim().isEmpty()) {
            throw new IllegalArgumentException("Stop name cannot be null or empty");
        }
        stops.add(stop);
        System.out.println("Stop added: " + stop + " (Position: " + stops.size() + ")");
    }

    // COLLECTION OPERATION: Retrieving elements
    public List<String> getStops() {
        return new ArrayList<>(stops); // Return copy
    }

    public String getNextStop(int currentIndex) {
        if (currentIndex >= 0 && currentIndex < stops.size() - 1) {
            return stops.get(currentIndex + 1);
        }
        return null;
    }

    // COLLECTION OPERATION: Removing elements
    public boolean removeStop(String stop) {
        boolean removed = stops.remove(stop);
        if (removed) {
            System.out.println("Stop removed: " + stop);
        }
        return removed;
    }

    public void displayStops() {
        System.out.println("Train " + getPlateNumber() + " stops:");
        for (int i = 0; i < stops.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + stops.get(i));
        }
    }

    @Override
    public void move() throws InsufficientFuelException {
        if (delayed) {
            System.out.println("Train " + getPlateNumber() + " cannot move - currently delayed!");
            return;
        }
        checkFuelForMovement();
        consumeFuel(20);
        System.out.println("Train " + getPlateNumber() + " is moving on rails with " + cars + " cars. Schedule: " + schedule);
        if (!stops.isEmpty()) {
            System.out.println("Stops: " + String.join(" → ", stops));
        }
    }

    public void delayTrain(String reason) {
        if (reason == null || reason.trim().isEmpty()) {
            reason = "Unknown reason";
        }
        delayed = true;
        System.out.println("Train " + getPlateNumber() + " delayed! Reason: " + reason);
    }

    public void resumeSchedule() {
        delayed = false;
        System.out.println("Train " + getPlateNumber() + " resumed normal schedule.");
    }
}
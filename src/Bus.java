import exceptions.CapacityExceededException;
import exceptions.InsufficientFuelException;
import java.util.ArrayList;
import java.util.List;

class Bus extends Vehicle {
    private int capacity;
    private Route route;
    
    /**
     * COLLECTION TYPE: List<Passenger>
     * RELATIONSHIP: One-to-Many (One Bus → Many Passengers)
     * JUSTIFICATION: 
     * - List is used because a bus can have multiple passengers
     * - Order matters (boarding sequence, seat assignment)
     * - Duplicates are not expected but List allows flexibility
     * - We need indexed access for operations like removing specific passengers
     * - ArrayList provides efficient random access O(1) and dynamic sizing
     */
    private List<Passenger> passengers;

    public Bus(String plateNumber, int fuelLevel, int capacity, Route route) {
        super(plateNumber, fuelLevel);
        if (capacity <= 0) {
            throw new IllegalArgumentException("Bus capacity must be positive");
        }
        if (route == null) {
            throw new IllegalArgumentException("Route cannot be null");
        }
        this.capacity = capacity;
        this.route = route;
        this.passengers = new ArrayList<>(); // ArrayList for efficient indexed access
    }

    // COLLECTION OPERATION: Adding elements
    public void addPassenger(Passenger p) throws CapacityExceededException {
        if (p == null) {
            throw new IllegalArgumentException("Passenger cannot be null");
        }
        if (passengers.size() >= capacity) {
            throw new CapacityExceededException("Bus is full. Capacity: " + capacity);
        }
        passengers.add(p);
        System.out.println(p.getName() + " boarded the bus. Passengers: " + passengers.size() + "/" + capacity);
    }

    // COLLECTION OPERATION: Removing elements
    public void removePassenger(Passenger p) {
        if (passengers.remove(p)) {
            System.out.println(p.getName() + " has alighted from the bus. Remaining: " + passengers.size());
        } else {
            System.out.println(p.getName() + " is not on this bus.");
        }
    }

    // COLLECTION OPERATION: Retrieving elements
    public List<Passenger> getPassengers() {
        return new ArrayList<>(passengers); // Return copy to protect internal state
    }

    public int getPassengerCount() {
        return passengers.size();
    }

    public int getAvailableSeats() {
        return capacity - passengers.size();
    }

    public boolean hasPassenger(Passenger p) {
        return passengers.contains(p);
    }

    public void displayPassengers() {
        System.out.println("Bus " + getPlateNumber() + " passengers (" + passengers.size() + "/" + capacity + "):");
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + passengers.get(i).getName() + " (ID: " + passengers.get(i).getId() + ")");
        }
    }

    @Override
    public void move() throws InsufficientFuelException {
        if (passengers.isEmpty()) {
            System.out.println("Bus " + getPlateNumber() + " cannot move without passengers.");
            return;
        }
        checkFuelForMovement();
        consumeFuel(15);
        System.out.println("Bus " + getPlateNumber() + " is carrying " + passengers.size() + " passengers.");
        route.displayRoute();
    }

    public Route getRoute() {
        return route;
    }
}

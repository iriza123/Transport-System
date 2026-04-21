import exceptions.CapacityExceededException;
import exceptions.InsufficientFuelException;
import java.util.ArrayList;
import java.util.List;

class Bus extends Vehicle {
    private int capacity;
    private Route route;
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
        this.passengers = new ArrayList<>();
    }

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

    public int getAvailableSeats() {
        return capacity - passengers.size();
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
}

import exceptions.InsufficientFuelException;
import exceptions.VehicleNotAvailableException;

class Taxi extends Vehicle {
    private String driverName;
    private boolean available;
    private Passenger currentPassenger;

    public Taxi(String plateNumber, int fuelLevel, String driverName, boolean available) {
        super(plateNumber, fuelLevel);
        if (driverName == null || driverName.trim().isEmpty()) {
            throw new IllegalArgumentException("Driver name cannot be null or empty");
        }
        this.driverName = driverName;
        this.available = available;
        this.currentPassenger = null;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getDriverName() {
        return driverName;
    }

    @Override
    public void move() throws InsufficientFuelException {
        checkFuelForMovement();
        consumeFuel(5);
        System.out.println("Taxi " + getPlateNumber() + " driven by " + driverName + " is transporting a passenger.");
    }

    public void acceptBooking(Passenger p, Route r) throws VehicleNotAvailableException, InsufficientFuelException {
        if (p == null) {
            throw new IllegalArgumentException("Passenger cannot be null");
        }
        if (r == null) {
            throw new IllegalArgumentException("Route cannot be null");
        }
        if (!available) {
            throw new VehicleNotAvailableException("Taxi " + getPlateNumber() + " is not available. Currently serving another passenger.");
        }
        
        int requiredFuel = (int) Math.ceil(r.getDistance() / 10);
        if (getFuelLevel() < requiredFuel) {
            throw new InsufficientFuelException("Insufficient fuel for this route. Required: " + requiredFuel + ", Available: " + getFuelLevel());
        }

        System.out.println("Taxi " + getPlateNumber() + " booked by " + p.getName() + " (Driver: " + driverName + ")");
        r.displayRoute();
        available = false;
        currentPassenger = p;
        consumeFuel(requiredFuel);
    }

    public void completeTrip() {
        if (currentPassenger != null) {
            System.out.println("Trip completed for " + currentPassenger.getName());
            currentPassenger = null;
            available = true;
        }
    }
}

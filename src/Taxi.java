class Taxi extends Vehicle {
    private String driverName;
    private boolean available;

    public Taxi(String plateNumber, int fuelLevel, String driverName, boolean available) {
        super(plateNumber, fuelLevel);
        this.driverName = driverName;
        this.available = available;
    }

    @Override
    public void move() {
        System.out.println("Taxi " + getPlateNumber() + " is transporting a passenger.");
    }

    public void acceptBooking(Passenger p, Route r) {
        if (available) {
            System.out.println("Taxi booked by " + p.getName());
            r.displayRoute();
            available = false;
        } else {
            System.out.println("Taxi not available.");
        }
    }
}

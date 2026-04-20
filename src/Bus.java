class Bus extends Vehicle {
    private int capacity;
    private Route route;

    public Bus(String plateNumber, int fuelLevel, int capacity, Route route) {
        super(plateNumber, fuelLevel);
        this.capacity = capacity;
        this.route = route;
    }

    @Override
    public void move() {
        System.out.println("Bus " + getPlateNumber() + " is carrying passengers.");
        route.displayRoute();
    }
}

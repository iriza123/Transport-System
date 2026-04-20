class Train extends Vehicle {
    private String schedule;
    private int cars;

    public Train(String plateNumber, int fuelLevel, String schedule, int cars) {
        super(plateNumber, fuelLevel);
        this.schedule = schedule;
        this.cars = cars;
    }

    @Override
    public void move() {
        System.out.println("Train " + getPlateNumber() + " is moving on rails. Schedule: " + schedule);
    }

    public void delayTrain() {
        System.out.println("Train delayed!");
    }
}
import exceptions.InsufficientFuelException;

class Train extends Vehicle {
    private String schedule;
    private int cars;
    private boolean delayed;

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
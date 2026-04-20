abstract class Vehicle {
    private String plateNumber;
    private int fuelLevel;

    public Vehicle(String plateNumber, int fuelLevel) {
        this.plateNumber = plateNumber;
        this.fuelLevel = fuelLevel;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getFuelLevel() {
        return fuelLevel;
    }

    public void refuel(int amount) {
        fuelLevel += amount;
        System.out.println("Refueled. Fuel level: " + fuelLevel);
    }

    public abstract void move(); // Abstraction

    public void displayInfo() {
        System.out.println("Vehicle Plate: " + plateNumber);
    }
}
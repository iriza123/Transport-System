import exceptions.InsufficientFuelException;

class Passenger {
    private String name;
    private String id;

    public Passenger(String name, String id) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger name cannot be null or empty");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger ID cannot be null or empty");
        }
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void bookTrip(Vehicle v) throws InsufficientFuelException {
        if (v == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        System.out.println(name + " (ID: " + id + ") booked a trip.");
        v.move();
    }
}

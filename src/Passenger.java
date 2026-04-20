class Passenger {
    private String name;

    public Passenger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void bookTrip(Vehicle v) {
        System.out.println(name + " booked a trip.");
        v.move();
    }
}

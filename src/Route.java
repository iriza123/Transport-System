class Route {
    private String origin;
    private String destination;
    private double distance;

    public Route(String origin, String destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    public void displayRoute() {
        System.out.println("Route: " + origin + " to " + destination + " (" + distance + " km)");
    }
}

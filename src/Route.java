import exceptions.InvalidRouteException;

class Route {
    private String origin;
    private String destination;
    private double distance;

    public Route(String origin, String destination, double distance) throws InvalidRouteException {
        if (origin == null || origin.trim().isEmpty()) {
            throw new InvalidRouteException("Origin cannot be null or empty");
        }
        if (destination == null || destination.trim().isEmpty()) {
            throw new InvalidRouteException("Destination cannot be null or empty");
        }
        if (distance <= 0) {
            throw new InvalidRouteException("Distance must be positive");
        }
        if (origin.equalsIgnoreCase(destination)) {
            throw new InvalidRouteException("Origin and destination cannot be the same");
        }
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    public void displayRoute() {
        System.out.println("Route: " + origin + " to " + destination + " (" + distance + " km)");
    }
}

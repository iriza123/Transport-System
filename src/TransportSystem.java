public class TransportSystem {
    public static void main(String[] args) {


        Route route1 = new Route("Kigali", "Huye", 130);


        Bus bus = new Bus("RAB123A", 50, 30, route1);
        Train train = new Train("RAD789C", 100, "10:00 AM", 8);
        Taxi taxi = new Taxi("RAC456B", 40, "Eric", true);


        Passenger p1 = new Passenger("John");


        Vehicle[] vehicles = {bus, train, taxi};

        for (Vehicle v : vehicles) {
            v.move();
            v.displayInfo();
        }

        System.out.println("----- Booking -----");

        p1.bookTrip(bus);
        taxi.acceptBooking(p1, route1);
        train.delayTrain();
        bus.refuel(20);
        System.out.println("Fuel level: " + bus.getFuelLevel());
    }
}

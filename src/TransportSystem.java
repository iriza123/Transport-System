import exceptions.*;

public class TransportSystem {
    public static void main(String[] args) {
        try {

            Route route1 = new Route("Kigali", "Huye", 130);
            Route route2 = new Route("Kigali", "Musanze", 90);


            Bus bus = new Bus("RAB123A", 50, 30, route1);
            Train train = new Train("RAD789C", 100, "10:00 AM", 8);
            Taxi taxi1 = new Taxi("RAC456B", 40, "Eric", true);
            Taxi taxi2 = new Taxi("RAC789D", 15, "Marie", false); // Not available


            Passenger p1 = new Passenger("John", "P001");
            Passenger p2 = new Passenger("Alice", "P002");
            Passenger p3 = new Passenger("Bob", "P003");

            System.out.println("===== TRANSPORT SYSTEM DEMO =====\n");


            System.out.println("----- Bus Operations -----");
            try {
                bus.addPassenger(p1);
                bus.addPassenger(p2);
                System.out.println("Available seats: " + bus.getAvailableSeats());
                bus.move();
                bus.displayInfo();
            } catch (CapacityExceededException | InsufficientFuelException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("\n----- Taxi Booking (Available) -----");
            try {

                if (taxi1.isAvailable()) {
                    taxi1.acceptBooking(p3, route2);
                    taxi1.move();
                    taxi1.completeTrip();
                }
            } catch (VehicleNotAvailableException | InsufficientFuelException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("\n----- Taxi Booking (Not Available) -----");
            try {

                taxi2.acceptBooking(p1, route1);
            } catch (VehicleNotAvailableException | InsufficientFuelException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("\n----- Train Operations -----");
            try {
                train.delayTrain("Track maintenance");
                train.move();
                train.resumeSchedule();
                train.move();
                train.displayInfo();
            } catch (InsufficientFuelException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println("\n----- Refueling -----");
            bus.refuel(20);
            System.out.println("Bus fuel level: " + bus.getFuelLevel());

            System.out.println("\n----- Exception Handling Demo -----");
            try {

                Route invalidRoute = new Route("Kigali", "Kigali", 100);
            } catch (InvalidRouteException e) {
                System.out.println("Caught exception: " + e.getMessage());
            }


            Taxi lowFuelTaxi = new Taxi("RAC111E", 5, "David", true);
            try {
                lowFuelTaxi.move();
            } catch (InsufficientFuelException e) {
                System.out.println("Caught exception: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

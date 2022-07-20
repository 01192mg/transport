package org.example;

public class Passenger {
    private final int personnel;
    private final String destination;
    private final String destinationDistance;

    public Passenger(int personnel) {
        this(personnel, "", "0km");
    }

    public Passenger(int personnel, String destination, String distance) {
        this.personnel = personnel;
        this.destination = destination;
        this.destinationDistance = distance;
    }

    public int getPersonnel() {
        return personnel;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestinationDistance() {
        return destinationDistance;
    }
}

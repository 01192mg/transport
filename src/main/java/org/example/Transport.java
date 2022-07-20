package org.example;

import java.util.UUID;

public abstract class Transport {
    private final String number;
    protected int fuel;
    protected int speed;
    protected int baseFare;
    protected int maxPersonnel;
    protected String state;
    protected Passenger passenger;
    protected int fare;

    protected Transport() {
        this.number = UUID.randomUUID().toString();
        this.fuel = 100;
        this.speed = 0;
    }

    public String getNumber() {
        return number;
    }

    public void take(Passenger passenger) {
        try {
            checkPersonnel(passenger.getPersonnel());
            checkStatus();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.passenger = new Passenger(0);
            return;
        }
        this.passenger = passenger;
        this.fare = calcFare();
    }

    protected abstract void checkPersonnel(int personnel) throws IllegalArgumentException;

    protected abstract void checkStatus() throws IllegalArgumentException;

    protected abstract int calcFare();

    public void addSpeed(int speed) {

    }

    public void addFuel(int fuel) {
        this.fuel += fuel;
        if (this.fuel < 10) {
            stopService();
        }
    }

    protected abstract void stopService();
}

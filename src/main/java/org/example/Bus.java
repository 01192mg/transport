package org.example;

public class Bus extends Transport {
    private int totalPersonnel;

    public Bus() {
        super();
        this.maxPersonnel = 30;
        this.state = "운행";
        this.baseFare = 1000;
    }

    public int getTotalPersonnel() {
        return totalPersonnel;
    }

    @Override
    public void take(Passenger passenger) {
        super.take(passenger);
        totalPersonnel += this.passenger.getPersonnel();
    }

    @Override
    protected void checkPersonnel(int personnel) throws IllegalArgumentException {
        if (this.maxPersonnel < this.totalPersonnel + personnel) {
            throw new IllegalArgumentException("[ALERT] 최대 승객 수 초과");
        }
    }

    @Override
    protected void checkStatus() throws IllegalArgumentException {
        if (this.state.equals("차고지행")) {
            throw new IllegalStateException("[ALERT] 운행중이 아닙니다.");
        }
    }

    @Override
    protected int calcFare() {
        return this.baseFare * this.passenger.getPersonnel();
    }

    @Override
    protected void stopService() {
        this.state = "차고지행";
        totalPersonnel = 0;
        this.passenger = null;
        fare = 0;
        System.out.println("[ALERT] 주유 필요");
    }
}

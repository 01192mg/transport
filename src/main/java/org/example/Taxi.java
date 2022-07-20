package org.example;

public class Taxi extends Transport {
    private final int basicDistance;
    private final int distanceFare;
    private int totalFare;

    public Taxi() {
        super();
        this.basicDistance = 1;
        this.state = "일반";
        this.baseFare = 3000;
        this.distanceFare = 1000;
        this.maxPersonnel = 4;
    }

    public int getTotalFare() {
        return totalFare;
    }

    @Override
    public void take(Passenger passenger) {
        super.take(passenger);
        if (this.passenger.getPersonnel() != 0) {
            this.state = "운행중";
        }
    }

    @Override
    protected void checkPersonnel(int personnel) throws IllegalArgumentException {
        if (this.maxPersonnel < personnel) {
            throw new IllegalArgumentException("[ALERT] 최대 승객 수 초과");
        }
    }

    @Override
    protected void checkStatus() throws IllegalArgumentException {
        if (this.state.equals("운행중")) {
            throw new IllegalStateException("[ALERT] 택시가 이미 운행 중입니다.");
        }
        if (this.state.equals("운행불가")) {
            throw new IllegalStateException("[ALERT] 주유가 필요합니다.");
        }
    }

    @Override
    protected int calcFare() {
        int distance = calcDistance() - this.basicDistance;
        return this.baseFare + (this.distanceFare * distance);
    }

    private int calcDistance() {
        String distanceKm = this.passenger.getDestinationDistance();
        int distance = Integer.parseInt(distanceKm.substring(0, distanceKm.length() - 2));
        if (distance < basicDistance) {
            distance = basicDistance;
        }
        return distance;
    }

    @Override
    protected void stopService() {
        this.state = "운행불가";
        passenger = null;
        System.out.println("[ALERT] 주유 필요");
    }

    @Override
    public void changeState(String state) {
        if (!state.equals("일반") && !state.equals("운행중") && !state.equals("운행불가")) {
            System.out.println("[ALERT] 상태는 \"일반\", \"운행불가\", \"운행중\"으로만 변경 가능합니다.");
            return;
        }
        if (state.equals("일반") || state.equals("운행중")) {
            if (this.fuel < 10) {
                System.out.println("[ALERT] 주유 필요");
                return;
            }
        }
        this.state = state;
    }

    public void payment() {
        this.totalFare += this.fare;
        if (this.fuel < 10) {
            this.state = "운행불가";
        } else {
            this.state = "일반";
        }
    }
}

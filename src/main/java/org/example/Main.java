package org.example;

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus();
        Bus bus2 = new Bus();
        System.out.println("bus number = " + bus.getNumber());
        System.out.println("bus2 number = " + bus2.getNumber());

        bus.take(new Passenger(2));
        System.out.println("탑승 승객 수 = " + bus.passenger.getPersonnel());
        System.out.println("잔여 승객 수 = " + (bus.maxPersonnel - bus.passenger.getPersonnel()));
        System.out.println("요금 확인 = " + bus.fare);

        bus.addFuel(-50);
        System.out.println("주유량 = " + bus.fuel);

        bus.state = "차고지행";
        bus.addFuel(10);
        System.out.println("상태 = " + bus.state);
        System.out.println("주유량 = " + bus.fuel);

        bus.state = "운행중";
        bus.take(new Passenger(45));

        bus.take(new Passenger(5));
        System.out.println("탑승 승객 수 = " + bus.passenger.getPersonnel());
        System.out.println("잔여 승객 수 = " + (bus.maxPersonnel - bus.passenger.getPersonnel()));
        System.out.println("요금 확인 = " + bus.fare);

        bus.addFuel(-55);
        System.out.println("주유량 = " + bus.fuel);
        System.out.println("상태 = " + bus.state);

        System.out.println("================택시 시작=============");

        Taxi taxi = new Taxi();
        Taxi taxi2 = new Taxi();
        System.out.println("taxi number = " + taxi.getNumber());
        System.out.println("taxi2 number = " + taxi2.getNumber());
        System.out.println("taxi fuel = " + taxi.fuel);
        System.out.println("taxi state = " + taxi.state);

        taxi.take(new Passenger(2, "서울역", "2km"));
        System.out.println("탑승 승객 수 = " + taxi.passenger.getPersonnel());
        System.out.println("잔여 승객 수 = " + (taxi.maxPersonnel - taxi.passenger.getPersonnel()));
        System.out.println("기본 요금 확인 = " + taxi.baseFare);
        System.out.println("목적지 = " + taxi.passenger.getDestination());
        System.out.println("목적지까지 거리 = " + taxi.passenger.getDestinationDistance());
        System.out.println("지불할 요금 = " + taxi.fare);
        System.out.println("상태 = " + taxi.state);

        taxi.addFuel(-80);
        taxi.payment();
        System.out.println("주유량 = " + taxi.fuel);
        System.out.println("누적 요금 = " + taxi.getTotalFare());

        taxi.take(new Passenger(5));
        taxi.take(new Passenger(3, "구로디지털단지역", "12km"));
        System.out.println("탑승 승객 수 = " + taxi.passenger.getPersonnel());
        System.out.println("잔여 승객 수 = " + (taxi.maxPersonnel - taxi.passenger.getPersonnel()));
        System.out.println("기본 요금 확인 = " + taxi.baseFare);
        System.out.println("목적지 = " + taxi.passenger.getDestination());
        System.out.println("목적지까지 거리 = " + taxi.passenger.getDestinationDistance());
        System.out.println("지불할 요금 = " + taxi.fare);

        taxi.addFuel(-20);
        taxi.payment();
        System.out.println("주유량 = " + taxi.fuel);
        System.out.println("상태 = " + taxi.state);
        System.out.println("누적 요금 = " + taxi.getTotalFare());
    }
}
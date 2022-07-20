package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TaxiTest {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(out));
    }

    @Test
    void taxiNumberTest() {
        //when
        Taxi taxi1 = new Taxi();
        Taxi taxi2 = new Taxi();
        //then
        assertNotEquals(taxi1.getNumber(), taxi2.getNumber());
    }

    @Test
    void takeTest() {
        //given
        Taxi taxi = new Taxi();
        Passenger passenger = new Passenger(2, "서울역", "2km");
        //when
        taxi.take(passenger);
        //then
        System.out.println(taxi.passenger);
        assertEquals(2, taxi.passenger.getPersonnel());
        assertEquals(2, taxi.maxPersonnel - taxi.passenger.getPersonnel());
        assertEquals(3000, taxi.baseFare);
        assertEquals("서울역", taxi.passenger.getDestination());
        assertEquals("2km", taxi.passenger.getDestinationDistance());
        assertEquals(4000, taxi.fare);
        assertEquals("운행중", taxi.state);
    }

    @Test
    void addFuelTest() {
        //given
        Taxi taxi = new Taxi();
        //when
        taxi.addFuel(-50);
        //then
        assertEquals(50, taxi.fuel);
    }

    @Test
    void setStateTest() {
        //given
        Taxi taxi = new Taxi();
        //when
        taxi.changeState("운행불가");
        //then
        assertEquals("운행불가", taxi.state);
    }

    @Test
    void changeStateIllegalStateAlertTest() {
        //given
        Taxi taxi = new Taxi();
        taxi.fuel = 0;
        //when
        taxi.changeState("운행중");
        //then
        assertEquals("[ALERT] 주유 필요\n", out.toString());
    }

    @Test
    void changeStateIllegalArgumentAlertTest() {
        //given
        Taxi taxi = new Taxi();
        //when
        taxi.changeState("견인");
        //then
        assertEquals("[ALERT] 상태는 \"일반\", \"운행불가\", \"운행중\"으로만 변경 가능합니다.\n", out.toString());
    }

    @Test
    void paymentTest() {
        //given
        Taxi taxi = new Taxi();
        Passenger passenger1 = new Passenger(1, "서울역", "1km");
        taxi.take(passenger1);
        taxi.payment();
        Passenger passenger2 = new Passenger(2, "서울역", "2km");
        taxi.take(passenger2);
        //when
        taxi.payment();
        //then
        assertEquals(7000, taxi.getTotalFare());
    }

    @Test
    void checkPersonnelTest() {
        //given
        Taxi taxi = new Taxi();
        Passenger passenger = new Passenger(31, "부산역", "20km");
        //when
        taxi.take(passenger);
        //then
        assertEquals("[ALERT] 최대 승객 수 초과\n", out.toString());
    }

    @Test
    void checkStatusTest() {
        //given
        Bus bus = new Bus();
        Taxi taxi = new Taxi();

        bus.state = "차고지행";
        Passenger passenger = new Passenger(1);
        //when
        bus.take(passenger);
        //then
        assertEquals("[ALERT] 운행중이 아닙니다.\n", out.toString());
    }

    @Test
    void fuelMessageTest() {
        //given
        Taxi taxi = new Taxi();
        //when
        taxi.addFuel(-91);
        //then
        assertEquals("[ALERT] 주유 필요\n", out.toString());
        assertEquals("운행불가", taxi.state);
    }
}

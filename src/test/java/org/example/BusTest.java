package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BusTest {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(out));
    }

    @Test
    void busNumberTest() {
        //when
        Bus bus1 = new Bus();
        Bus bus2 = new Bus();
        //then
        assertNotEquals(bus1.getNumber(), bus2.getNumber());
    }
    @Test
    void takeTest() {
        //given
        Bus bus = new Bus();
        Passenger passenger = new Passenger(2);
        //when
        bus.take(passenger);
        //then
        assertEquals(2, bus.passenger.getPersonnel());
        assertEquals(28, bus.maxPersonnel - bus.getTotalPersonnel());
        assertEquals(2000, bus.fare);
    }

    @Test
    void addFuelTest() {
        //given
        Bus bus = new Bus();
        //when
        bus.addFuel(-50);
        //then
        assertEquals(50, bus.fuel);
    }

    @Test
    void setStateTest() {
        //given
        Bus bus = new Bus();
        //when
        bus.state = "차고지행";
        //then
        assertEquals("차고지행", bus.state);
    }

    @Test
    void checkPersonnelTest() {
        //given
        Bus bus = new Bus();
        Passenger passenger = new Passenger(31);
        //when
        bus.take(passenger);
        //then
        assertEquals("[ALERT] 최대 승객 수 초과\n", out.toString());
    }

    @Test
    void checkStatusTest() {
        //given
        Bus bus = new Bus();
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
        Bus bus = new Bus();
        //when
        bus.addFuel(-91);
        //then
        assertEquals("[ALERT] 주유 필요\n", out.toString());
        assertEquals("차고지행", bus.state);
    }
}

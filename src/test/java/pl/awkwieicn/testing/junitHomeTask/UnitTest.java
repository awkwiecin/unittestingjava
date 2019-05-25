package pl.awkwieicn.testing.junitHomeTask;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class UnitTest {

        static Coordinates coordinates;
        static Cargo cargo;
        static Unit unit;

        @BeforeAll
        void setUp() {
            coordinates = new Coordinates(2,3);
            cargo = new Cargo("Banany", 12);
            unit = new Unit(coordinates, 30, 20);
        }


    @Test
    void coordinatesCannotBeBiggerThanFuel() {

        //given
        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.move(50, 50));

    }

    @Test
    void tankingUpIncreasesFuel() {
        //given
        //when
        unit.move(12, 12);
        int fuelBuff = unit.getFuel();
        unit.tankUp();

        //then
        assertThat(unit.getFuel(), greaterThan(fuelBuff));
    }

    @Test
    void cargoCannotBeBiggerThanMaxCargo() {

        //given
        //when
        //then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(cargo));
    }



    @Test
    void unloadingCargoMakesLesserWeight() {

        //given
        Cargo cargo1 = new Cargo("Michael", 6);

        //when
        unit.loadCargo(cargo);
        unit.loadCargo(cargo1);
        int weightBuff = unit.getLoad();
        unit.unloadCargo(cargo);

        //then
        assertThat(unit.getLoad(), lessThan(weightBuff));

    }

    @Test
    void cargoIsProperlyRemoved() {

        //given
        //when
        unit.loadCargo(cargo);
        unit.unloadAllCargo();

        //then
        assertThat(unit.getLoad(),equalTo(0));

    }


}
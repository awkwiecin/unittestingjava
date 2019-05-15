package pl.awkwieicn.testing.junitHomeTask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class UnitTest {
    /*
        @BeforeEach
        void setup() {
            Coordinates coordinates = new Coordinates(2,3);
            Cargo cargo = new Cargo("Banany", 12);
            Unit unit = new Unit(coordinates, 30, 20);
        }*/


    @Test
    void coordinatesCannotBeBiggerThanFuel() {

        Coordinates coordinates = new Coordinates(2, 3);
        Unit unit = new Unit(coordinates, 30, 20);
        assertThrows(IllegalStateException.class, () -> unit.move(50, 50));

    }

    @Test
    void tankingUpIncreasesFuel() {
        Coordinates coordinates = new Coordinates(2, 3);
        Unit unit = new Unit(coordinates, 30, 20);
        unit.move(12, 12);
        int fuelBuff = unit.getFuel();
        unit.tankUp();
        assertThat(unit.getFuel(), greaterThan(fuelBuff));
    }

    @Test
    void cargoCannotBeBiggerThanMaxCargo() {

        Coordinates coordinates = new Coordinates(2, 3);
        Cargo cargo = new Cargo("Banany", 40);
        Unit unit = new Unit(coordinates, 30, 20);

        assertThrows(IllegalStateException.class, () -> unit.loadCargo(cargo));
    }



    @Test
    void unloadingCargoMakesLesserWeight() {

        Coordinates coordinates = new Coordinates(2, 3);
        Cargo cargo = new Cargo("Banany", 12);
        Cargo cargo1 = new Cargo("Michael", 6);
        Unit unit = new Unit(coordinates, 30, 20);

        unit.loadCargo(cargo);
        unit.loadCargo(cargo1);
        int weightBuff = unit.getLoad();
        unit.unloadCargo(cargo);

        assertThat(unit.getLoad(), lessThan(weightBuff));

    }

    @Test
    void cargoIsProperlyRemoved() {

        Coordinates coordinates = new Coordinates(2, 3);
        Cargo cargo = new Cargo("Banany", 12);
        Unit unit = new Unit(coordinates, 30, 20);

        unit.loadCargo(cargo);
        unit.unloadAllCargo();
        assertThat(unit.getLoad(),equalTo(0));

    }


}
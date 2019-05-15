package pl.awkwieicn.testing.junitHomeTask;

import jdk.jfr.Label;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {


    @Test
    @Label("Checking positivity of both coordinates")
    void coordinatesShouldNotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-2,-5));
    }

    @Test
    void coordinatesCannotBeBiggerThan100() {
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(101,101));
    }

    @Test
    void copyingFunctionAddsGivenCoordinatesToExistingOnes() {

        Coordinates coordinates = new Coordinates(2,5);
        Coordinates coordinates1 = new Coordinates(3,7);
        Coordinates coordinatesCopied = coordinates.copy(coordinates,1,2);

        assertThat(coordinates1, equalTo(coordinatesCopied));
    }
}
package pl.awkwieicn.testing.mockitoHomeTask;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import java.util.Optional;
import java.util.NoSuchElementException;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UnitServiceTest {
    @InjectMocks
    UnitService unitService;

    @Mock
    UnitRepository unitRepositoryMock;
    @Mock
    CargoRepository cargoRepositoryMock;

    private static Coordinates coordinates;
    private static Unit unit;
    private static Cargo cargo;

    @BeforeAll
    static void setUp() throws Exception{
        coordinates = new Coordinates(2,3);
        unit = new Unit(coordinates,12,4);
        cargo = new Cargo("banany",12);
    }

    @Test
    void throwExceptionifCargoNotPresent() {

        //given
        given(unitRepositoryMock.getUnitByCoordinates(coordinates)).willReturn(unit);
        given(cargoRepositoryMock.findCargoByName("name")).willReturn(null);

        //when


        //then
        assertThrows(NoSuchElementException.class, () -> unitService.addCargoByName(unit,"name"));

    }

    @Test
    void getUnitOn() {
    }
}
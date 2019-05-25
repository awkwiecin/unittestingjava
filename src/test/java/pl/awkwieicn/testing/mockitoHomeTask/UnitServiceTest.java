package pl.awkwieicn.testing.mockitoHomeTask;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
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
    static void setUp() throws Exception {
        coordinates = new Coordinates(2, 3);
        unit = new Unit(coordinates, 12, 4);
        cargo = new Cargo("banany", 3);
    }

    @Test
    void throwExceptionifCargoNotPresent() {

        //given
        given(cargoRepositoryMock.findCargoByName("banany")).willReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.addCargoByName(unit, "banany"));

    }

    @Test
    void returnCargoIfPresent() {

        //given
        Optional<Cargo> cargoOptional = Optional.ofNullable(cargo);

        given(cargoRepositoryMock.findCargoByName("banany")).willReturn(cargoOptional);


        //when
        //then
        //assertThat(cargoRepositoryMock.findCargoByName("banany"), not(equalTo(Optional.empty())));
        assertDoesNotThrow(() -> unitService.addCargoByName(unit, "banany"));
    }

    @Test
    void throwExceptionIfUnitIsNull() {

        //given
        given(unitRepositoryMock.getUnitByCoordinates(coordinates)).willReturn(null);

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.getUnitOn(coordinates));

    }

    @Test
    void returnUnitWhenPresent() {

        //given
        given(unitRepositoryMock.getUnitByCoordinates(coordinates)).willReturn(unit);

        //when
        //then
        assertThat(unitService.getUnitOn(coordinates), equalTo(unit));
    }

}
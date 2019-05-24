package pl.awkwieicn.testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import pl.awkwieicn.testing.extensions.IAExceptionIgnoreExtension;
import pl.awkwieicn.testing.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class MealTest {

    @Spy
    private Meal mealSpy;

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);
        assertThat(discountedPrice, equalTo(28));
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {
        //given
        Meal meal1 = new Meal(12);
        Meal meal2 = meal1;

        assertSame(meal1, meal2);
        assertThat(meal1, is(meal2));

    }

    @Test
    void referencesToTheSameObjectShouldNotBeEqual() {
        //given
        Meal meal1 = new Meal(12);
        Meal meal2 = new Meal(15);

        //them
        assertNotSame(meal1, meal2);
        assertThat(meal1, not(equalTo(meal2)));

    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
        //given
        Meal meal1 = new Meal(12, "Spaghetti");
        Meal meal2 = new Meal(12, "Spaghetti");

        //then
        assertEquals(meal1, meal2, "Checking if two meals are equal");
        assertThat(meal1, equalTo(meal2));
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {
        //given
        Meal meal = new Meal(8, "Soup");
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(1000));

    }


    @ParameterizedTest
    @MethodSource("createMealWithNameAndPrice")
    void burgerShouldHaveCorrectNameAndPrice(String name, int price) {
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name) {
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    private static Stream<String> createCakeNames() {
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 8})
    @ExtendWith(IAExceptionIgnoreExtension.class)
    void mealPricesShouldBeLowerThan10(int price) {
        if (price > 5) {
            throw new IllegalArgumentException();
        }

        assertThat(price, lessThan(20));
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollections() {
        return Arrays.asList(
                dynamicTest("Dynamic test 1", () -> assertThat(5, lessThan(6))),
                dynamicTest("Dynamic test 2", () -> assertEquals(4, 2 * 2))
        );
    }

    @Tag("fries")
    @TestFactory
    Collection<DynamicTest> calculateMealPrices() {
        Order order = new Order();
        order.addMealToOrder(new Meal(10, 2, "Hamburger"));
        order.addMealToOrder(new Meal(7, 4, "Fries"));
        order.addMealToOrder(new Meal(22, 3, "Pizza"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(67));
            };

            String name = "Test name: " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;
    }

    @Test
    void testMealSumPrice() {

        //given
        Meal meal = mock(Meal.class);

        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);

        given(meal.sumPrice()).willCallRealMethod();
        //when
        int result = meal.sumPrice();

        //then
        assertThat(result, equalTo(45));

    }

    @Test
    @ExtendWith(MockitoExtension.class)
    void testMealSumPriceWithSpy() {

        //given
       // Meal meal = spy(Meal.class);
        given(mealSpy.getPrice()).willReturn(15);
        given(mealSpy.getQuantity()).willReturn(3);


        //when
        int result = mealSpy.sumPrice();

        //then
        then(mealSpy).should().getPrice();
        then(mealSpy).should().getQuantity();
        assertThat(result, equalTo(45));

    }

    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }

}
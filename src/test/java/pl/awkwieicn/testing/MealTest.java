package pl.awkwieicn.testing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {

        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);
        assertThat(discountedPrice).isEqualTo(28);
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {
        //given
        Meal meal1 = new Meal(12);
        Meal meal2 = meal1;

        assertSame(meal1,meal2);
        assertThat(meal1).isSameAs(meal2);

    }

    @Test
    void referencesToTheSameObjectShouldNotBeEqual() {
        //given
        Meal meal1 = new Meal(12);
        Meal meal2 = new Meal(15);

        //them
        assertNotSame(meal1,meal2);
        assertThat(meal1).isNotSameAs(meal2);

    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
        //given
        Meal meal1 = new Meal(12,"Spaghetti");
        Meal meal2 = new Meal(12,"Spaghetti");

        //then
        assertEquals(meal1,meal2,"Checking if two meals are equal");
        assertThat(meal1).isEqualTo(meal2);
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice(){
        //given
        Meal meal = new Meal(8, "Soup");
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(1000));

    }

}
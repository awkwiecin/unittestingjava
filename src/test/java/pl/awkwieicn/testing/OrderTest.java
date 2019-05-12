package pl.awkwieicn.testing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OrderTest {

    @Test
    void testAssertArrayEquals() {

        //given
        int [] ints1 = {1,2,3};
        int [] ints2 = {1,2,3};

        //then
        assertArrayEquals(ints1,ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfORder() {
        //given
        Order order = new Order();

        assertThat(order.getMeals(),empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {
        //given
        Meal meal = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");
        Order order = new Order();

        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));

        assertThat(order.getMeals().get(0).getPrice(),equalTo(15));

    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        //given
        Meal meal = new Meal(15, "Burger");
        Order order = new Order();

        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {

        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");
        Meal meal3 = new Meal(10, "Piadina");

        Order order = new Order();

        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        order.addMealToOrder(meal3);

        assertThat(order.getMeals(),containsInAnyOrder(meal3,meal2,meal1));

    }

    @Test
    void testIfTwoMealListAreTheSame() {

        //given
        Meal meal1 = new Meal(15, "Burger");
        Meal meal2 = new Meal(5, "Sandwich");
        Meal meal3 = new Meal(10, "Piadina");

        List<Meal> meals1 = Arrays.asList(meal1,meal2);
        List<Meal> meals2 = Arrays.asList(meal1,meal2);

        assertThat(meals1, is(meals2));

    }

}

package pl.awkwieicn.testing.cart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.awkwieicn.testing.Meal;
import pl.awkwieicn.testing.order.Order;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

@DisplayName("Test cases for cart")
class CartTest {

    //@Disabled
    @DisplayName("cart is able to process 1000 orders in 100ms")
    @Test
    void simulateLargeOrder() {

        //given
        Cart cart = new Cart();

        //when
        //then
        assertTimeout(Duration.ofMillis(100), () -> cart.simulateLargeOrder());

    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOneToCart() {
        //given
        Order order = new Order();
        Cart cart = new Cart();


        //when
        cart.addOrderToCart(order);

        //then
        assertThat(cart.getOrders(),anyOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertThat(cart.getOrders(),allOf(
                notNullValue(),
                hasSize(1),
                is(not(empty())),
                is(not(emptyCollectionOf(Order.class)))
        ));

        assertAll( "This is a group of assertions for cart",
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders(), is(not(empty()))),
                () -> assertThat(cart.getOrders(), is(not(emptyCollectionOf(Order.class)))),
                () -> assertThat(cart.getOrders().get(0).getMeals(),empty()),
                () -> {
                    List<Meal> mealList = cart.getOrders().get(0).getMeals();
                    assertThat(mealList, empty());
                }
        );
    }
}
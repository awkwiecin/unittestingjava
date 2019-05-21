package pl.awkwieicn.testing.Cart;

import pl.awkwieicn.testing.Meal;
import pl.awkwieicn.testing.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Order> orders = new ArrayList<>();

    public void addOrderToCart(Order order) {
        this.orders.add(order);
    }

    void clearCart() {
        this.orders.clear();
    }

    public void simulateLargeOrder() {

        for (int i = 0; i < 1_000; i++) {
            Meal meal = new Meal(i % 10, "Hamburger no " + i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size: " + orders.size());
        clearCart();
    }

    public List<Order> getOrders() {
        return orders;
    }
}

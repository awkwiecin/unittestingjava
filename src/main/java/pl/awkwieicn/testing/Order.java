package pl.awkwieicn.testing;

import java.util.ArrayList;
import java.util.List;

class Order {

    private List<Meal> meals = new ArrayList<>();

    void addMealToOrder (Meal meal) {
        this.meals.add(meal);
    }

    void removeMealFromOrder(Meal meal) {
        this.meals.remove(meal);
    }

    List<Meal> getMeals() {
        return meals;
    }

    void cancel() {
        this.meals.clear();
    }

    int totalPrice() {
        int sum = this.meals.stream().mapToInt(meal -> meal.getPrice()).sum();
        if (sum<0)
            throw new IllegalStateException("Price limit exceeded");
        else
            return sum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }
}

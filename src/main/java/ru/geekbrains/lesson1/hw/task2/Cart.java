package ru.geekbrains.lesson1.hw.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    private final List<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    public Cart(Class<T> clazz, UMarket market) {
        this.market = market;
        foodstuffs = new ArrayList<>();
        this.clazz = clazz;
    }

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }

    public void printFoodstuffs() {
        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> {
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                    index.getAndIncrement(), food.getName(),
                    food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет",
                    food.getCarbohydrates() ? "Да" : "Нет");
        });
    }

    public void cardBalancing() {
        AtomicBoolean proteinsAdded = new AtomicBoolean(false);
        AtomicBoolean fatsAdded = new AtomicBoolean(false);
        AtomicBoolean carbohydratesAdded = new AtomicBoolean(false);

        if (foodstuffs.stream().anyMatch(Food::getProteins) &&
                foodstuffs.stream().anyMatch(Food::getFats) &&
                foodstuffs.stream().anyMatch(Food::getCarbohydrates)) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        Collection<T> marketList = market.getThings(clazz);
        List<T> missingMacronutrientFoods = marketList.stream()
                .filter(food -> {
                    if (!proteinsAdded.get() && food.getProteins()) {
                        proteinsAdded.set(true);
                        return true;
                    } else if (!fatsAdded.get() && food.getFats()) {
                        fatsAdded.set(true);
                        return true;
                    } else if (!carbohydratesAdded.get() && food.getCarbohydrates()) {
                        carbohydratesAdded.set(true);
                        return true;
                    }
                    return false;
                })
                .toList();

        foodstuffs.addAll(missingMacronutrientFoods);

        if (proteinsAdded.get() && fatsAdded.get() && carbohydratesAdded.get()) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }
    }

}

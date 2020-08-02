package ru.sgol;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<T> stock = new ArrayList();


    public void addFruit(T fruit) {
        stock.add(fruit);
    }

    public boolean compare(Box box) {
        return this.getTotalWeight() == box.getTotalWeight();
    }

    public void emptyBoxToAnotherBox (Box<T> box) {
        if (this.getTotalWeight() == 0){
            return;
        }

        box.getStock().addAll(this.stock);
        this.stock.clear();
    }

    public double getTotalWeight() {
        return stock.stream().mapToDouble(Fruit::getWeight).sum();
    }

    public List<T> getStock() {
        return stock;
    }
}

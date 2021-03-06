package ru.sgol;

import java.util.ArrayList;
import java.util.Arrays;

public class Application {

    /*
     *a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
     *b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
     *c. Для хранения фруктов внутри коробки можете использовать ArrayList;
     *d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
     *e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
     *f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
     *g. Не забываем про метод добавления фрукта в коробку.
     */
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Apple> appleBox2 = new Box<>();

        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
//        appleBox.addFruit(new Orange());

        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
//      orangeBox.addFruit(new Apple());

        appleBox2.addFruit(new Apple());
        appleBox2.addFruit(new Apple());
        appleBox2.addFruit(new Apple());

        System.out.println(appleBox2.compare(orangeBox));

        System.out.println(appleBox.getTotalWeight());
        System.out.println(appleBox2.getTotalWeight());

        appleBox.emptyBoxToAnotherBox(appleBox2);

        System.out.println(appleBox.getTotalWeight());
        System.out.println(appleBox2.getTotalWeight());
    }






    /*
     * Написать метод, который преобразует массив в ArrayList;
     */

    public <T> ArrayList<T> transformArrayToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    /*
     *
     * Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
     */

    public void switchElementsInArray(Object[] array, int i1, int i2) {
        Object buf = array[i1];
        array[i2] = array[i1];
        array[i1] = buf;
    }
}

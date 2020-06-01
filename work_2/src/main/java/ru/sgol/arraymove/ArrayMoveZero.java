package ru.sgol.arraymove;

import java.util.Arrays;

public class ArrayMoveZero {


    // Вариант неправильно интерпретированной задачи, когда освободившиеся элементы заполняли нулями

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(a));
        System.out.println();
        moveAllElementsOn(a, 3);
        System.out.println(Arrays.toString(a));
        moveAllElementsOn(a, -6);
        System.out.println();
        System.out.println(Arrays.toString(a));
    }

    private static void moveAllElementsOn(int[] mas, int n) {
        if (n > 0) {
            for (int i = mas.length - 1; i >= 0; i--) {
                if (i >= n) {
                    mas[i] = mas[i - n];
                } else {
                    mas[i] = 0;
                }
            }
        } else {
            for (int i = 0; i < mas.length; i++) {
                if (i - n < mas.length) {
                    mas[i] = mas[i - n];
                } else {
                    mas[i] = 0;
                }
            }
        }
    }
}

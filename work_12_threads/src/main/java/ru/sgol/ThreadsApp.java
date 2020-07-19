package ru.sgol;
/*

1. Необходимо написать два метода, которые делают следующее:
        1) Создают одномерный длинный массив, например:

        static final int size = 10000000;
        static final int h = size / 2;
        float[] arr = new float[size];

        2) Заполняют этот массив единицами;
        3) Засекают время выполнения: long a = System.currentTimeMillis();
        4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        5) Проверяется время окончания метода System.currentTimeMillis();
        6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);

        Отличие первого метода от второго:
        Первый просто бежит по массиву и вычисляет значения.
        Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы
        обратно в один.

        Пример деления одного массива на два:

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Пример обратной склейки:

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        Примечание:
        System.arraycopy() – копирует данные из одного массива в другой:
        System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение,
        откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
        По замерам времени:
        Для первого метода надо считать время только на цикл расчета:

        for (int i = 0; i < size; i++) {
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
*/

import java.util.Arrays;

public class ThreadsApp {

    static final int size = 10_000_000;
    static final int h = size / 2;
    static float[] arr1 = new float[h];
    static float[] arr2 = new float[h];
    static float[] arr = new float[size];

    public static void main(String[] args) {

        Arrays.fill(arr, 1);
        float[] arr1Rez = calcArrSingleThread();
        float[] arr2Rez = calcArrTwoThreads();
        System.out.println("массивы равны "+ Arrays.equals(arr1Rez,arr2Rez));
    }

    public static float[] calcArrSingleThread() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения в один поток: " + (endTime - startTime) + "мс");

        return arr;
    }


    public static float[] calcArrTwoThreads() {
        float[] arr = new float[size];

        Arrays.fill(arr, 1);

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float)(arr1[i] *
                            Math.sin(0.2f + i / 5) *
                            Math.cos(0.2f + i / 5) *
                            Math.cos(0.4f + i / 2));
                }
            }
        };
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float)(arr2[i] *
                            Math.sin(0.2f + (i + h) / 5) *
                            Math.cos(0.2f + (i + h) / 5) *
                            Math.cos(0.4f + (i + h) / 2));
                }
            }
        };


        long startTime = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения в два потока: " + (endTime - startTime) + "мс");

        return arr;
    }


}

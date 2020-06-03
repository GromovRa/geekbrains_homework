package ru.sgol;

public class RegularTask {


    public static void main(String[] args) {
        int[] array = {-1, 2, 3, 4, 5, 6, 7, 8, -9};
        printMaxMin(array);


        int[] mirroredArray = {1, 2, 3, 6};
        int[] ninMirroredArray = {1, 2, 3, 5};
        System.out.println(isArrayElementSumMirrored(mirroredArray));
        System.out.println(isArrayElementSumMirrored(ninMirroredArray));
    }

    /*
     * Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
     */
    public static void printMaxMin(int[] array) {
        int min = array[0];
        int max = array[0];

        for (int i : array) {
            if (max < i) {
                max = i;
            }
            if (min > i) {
                min = i;
            }
        }
        System.out.println("Минимальный элемент = " + min + " Максимальный = " + max);
    }

    /*
     * Написать метод, в который передается не пустой одномерный целочисленный массив,
     * метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
     * Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
     * граница показана символами ||, эти символы в массив не входят.
     */

    public static boolean isArrayElementSumMirrored(int[] array) {
        boolean isMirrored = false;

        int sumLeft = 0;
        for (int i = 0; i < array.length - 1; i++) {
            sumLeft += array[i];
            int sumRight = 0;
            for (int j = i + 1; j < array.length; j++) {
                sumRight += array[j];
            }
            if (sumRight == sumLeft) {
                isMirrored = true;
                break;
            }
        }
        return isMirrored;
    }


}

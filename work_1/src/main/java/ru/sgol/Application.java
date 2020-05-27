package ru.sgol;

public class Application {

    /*----Немного о притимивах----*/
    byte bMax = 127;
    byte bMin = -128;
    short sMax = 32767;
    short sMin = -32768;
    short sMinFromWrapper = Short.MIN_VALUE; // Удобно границу диапазона из класса-обёртки
    int iMax = 0x7FFF_FFFF; // Или использовать шестандцатеричный вид цифр, исходя из размера примитива (2_147_483_647)
    int iMin = 0x8000_0000; // -2_147_483_648
    long lMax = 0x7FFF_FFFF_FFFF_FFFFL; // Внезапно использовать разделитель можно и в щестнадцатеричном виде ((2^63)-1)
    long lMin = 0x8000_0000_0000_0000L; // Важно указывать у лонга L на конце (-2^63)
    float f = .6666f;   //  Для float важно отдельно указать f на конце. Запись ".***" экваивалента "0.***"
    double d = 1.1234624234124;
    char chr = 'h'; // Хранит символ. Как я понял - код символа. Размер - 2 байта и кодировка соответственно UTF16 (ASCII?)
    boolean bl = true; // логичейский примитив. (размер в спецификации не определён)

    Object obj = new String("String"); // Ссылочный тип данных. Всё есть Object. По факту - 4 байтная ссылка на область памяти

    public static void main(String[] args) {
    }

    private double doSomeMathCalc(double a, double b, double c, double d) {
        return a * (b + (c / d));
    }

    private boolean isNumberInLimit(int number) {
        return number >= 10 && number <= 20;
    }

    private void printIsNumberMoreOrEqualZero(int number) {
        System.out.println(number >= 0 ? "Число больше/равно нуля" : "Число меньше нуля"); // Пример использования тернарного оператора
    }

    private boolean isNumberNegative(int number) {
        return number < 0;
    }

    private void sayHelloToSpecificPerson(String name) {
        System.out.println("Привет, " + name);  // Наверное, лучше через StringBuilder
    }

    private void printIsYearLeap(int year) {
        if (year % 4 == 0 || year % 100 == 0 || year % 400 == 0) {
            System.out.println(year + " - високосный");
        } else {
            System.out.println(year + " - не високосный");
        }
    }
}

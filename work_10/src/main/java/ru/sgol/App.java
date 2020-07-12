package ru.sgol;

import java.util.*;

public class App {
    private static final String[] WORDS = {"Саша", "Петя", "Вася", "Катя", "Вова", "Миша", "Андрей", "Маша",
            "Саша", "Петя", "Вася", "Катя", "Вова", "Вова", "Миша", "Андрей", "Маша",
            "Саша", "Петя", "Вася", "Катя", "Вова", "Вова", "Миша", "Андрей", "Маша",
            "Саша", "Петя", "Вася", "Катя", "Вова", "Миша", "Андрей", "Маша",
            "THE ONLY ONE"
    };

    public static void main(String[] args) {
        Set<String> uniqueNamesSet = new HashSet<>(Arrays.asList(WORDS));

        System.out.println(uniqueNamesSet);

        Map<String, Integer> namesCountMap = new HashMap<>();

        for (String s : WORDS) {
            if (namesCountMap.containsKey(s)) {
                namesCountMap.put(s, namesCountMap.get(s) + 1);
            } else {
                namesCountMap.put(s, 1);
            }
        }
        System.out.println(namesCountMap);

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.putPersonInBook("Сергей", new Person("123456789", "oloFFlo@eamil.ru"));
        phoneBook.putPersonInBook("Петя", new Person("123432", "HH@eamil.ru"));
        phoneBook.putPersonInBook("Саша", new Person("51123456", "GG@eamil.ru"));
        phoneBook.putPersonInBook("Саша", new Person("123456789", "2@eamil.ru"));
        phoneBook.putPersonInBook("Саша", new Person("123456789", "dg@eamil.ru"));
        phoneBook.putPersonInBook("Катя", new Person("123456789", "2@eamil.ru"));
        phoneBook.putPersonInBook("Сергей", new Person("123456789", "gg@eamil.ru"));
        phoneBook.putPersonInBook("Вова", new Person("123456789", "YY@eamil.ru"));
        phoneBook.putPersonInBook("Вова", new Person("134513567", "TT@eamil.ru"));
        phoneBook.putPersonInBook("Миша", new Person("123456789", "RR@eamil.ru"));
        phoneBook.putPersonInBook("Сергей", new Person("123456789", "EE@eamil.ru"));
        phoneBook.putPersonInBook("Катя", new Person("123456789", "WW@eamil.ru"));
        phoneBook.putPersonInBook("THE ONLY ONE", new Person("123456789", "QQ@eamil.ru"));

        System.out.println("Вернем лист телефонов " + phoneBook.getEmailsByLastName("Саша"));

        System.out.println("Вернем лист емейлов " + phoneBook.getPhoneNumbersByLastName("Саша"));

        System.out.println("вернем пустой массив - нет такого пользователя " + phoneBook.getPhoneNumbersByLastName("Джим"));

    }
}

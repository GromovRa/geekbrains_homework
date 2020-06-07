package ru.sgol;

import java.math.BigDecimal;

public class Application {

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];

        employees[0] = new Employee("Дерри", "Панченко", "Георгиевна",
                "example1@mail.ru", "+7 (937) 801-51-32",
                26, new BigDecimal(80_000),
                "разработчик");

        employees[1] = new Employee("Кинис", "Власов", "Андреевич",
                "example2@mail.ru", "+7 (903) 558-85-97",
                42, new BigDecimal(85_000),
                "разработчик");

        employees[2] = new Employee("Лэмэриан", "Демидова", "Аркадьевна",
                "example3@mail.ru", "+7 (911) 567-17-19",
                30, new BigDecimal(100_000),
                "ведущий разработчик");

        employees[3] = new Employee("Манон", "Малышева", "Степановна",
                "example4@mail.ru", "+7 (958) 463-13-70",
                26, new BigDecimal(120_000),
                "ведущий разработчик");

        employees[4] = new Employee("Евфимья", "Вершинина", "Филипповна",
                "example5@mail.ru", "+7 (963) 223-64-81",
                42, new BigDecimal(150_000),
                "руководитель группы");


//        Arrays.stream(employees)
//                .filter(employee -> employee.age > 40)
//                .forEach(System.out::println);

        for (Employee employee : employees) {
            if (employee.age > 40) {
                System.out.println(employee);
            }
        }
    }
}

package ru.sgol;

import java.math.BigDecimal;

public class Employee {
    String name;
    String lastName;
    String middleName;
    String email;
    String phone;
    int age;
    BigDecimal payment;
    String position;

    public Employee(String name, String lastName, String middleName, String email, String phone, int age, BigDecimal payment, String position) {
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.payment = payment;
        this.position = position;
    }



    @Override
    public String toString () {
        return String.format("ФИО сотрудника: %s %s %s, должность: %s, email: %s, телефон: %s, зарплата: %s руб, возраст: %s лет",
                this.name, this.lastName, this.middleName,this.position, this.email, this.phone, this.payment, this.age);
    }

}
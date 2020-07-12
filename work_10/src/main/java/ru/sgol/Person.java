package ru.sgol;

import java.util.Objects;

public class Person {

    private String phone;
    private String email;

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Person(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(phone, person.phone) &&
                Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone, email);
    }
}

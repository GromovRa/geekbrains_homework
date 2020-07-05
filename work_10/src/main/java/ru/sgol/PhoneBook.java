package ru.sgol;

import java.util.*;

public class PhoneBook {
    HashMap<String, HashSet<Person>> phoneBookMap = new HashMap<>();

    public void putPersonInBook(String lastName, Person person) {
        if (phoneBookMap.containsKey(lastName)) {
            phoneBookMap.get(lastName).add(person);
        } else {
            phoneBookMap.put(lastName, new HashSet<>(Collections.singletonList(person)));
        }
    }

    public ArrayList<String> getPhoneNumbersByLastName(String lastName) /*throws Exception*/ {
//        if (!phoneBookMap.containsKey(lastName)) {
//            throw new Exception("Такого человека нет в книге");
//        }


        ArrayList<String> phoneNumbersList = new ArrayList<>();
        if (phoneBookMap.containsKey(lastName)) {
            for (Person p : phoneBookMap.get(lastName)) {
                phoneNumbersList.add(p.getPhone());
            }
        }

        return phoneNumbersList;
    }

    public ArrayList<String> getEmailsByLastName(String lastName) /*throws Exception*/ {
//        if (!phoneBookMap.containsKey(lastName)) {
//            throw new Exception("Такого человека нет в книге");
//        }

        ArrayList<String> emailsList = new ArrayList<>();
        if (phoneBookMap.containsKey(lastName)) {
            for (Person p : phoneBookMap.get(lastName)) {
                emailsList.add(p.getEmail());
            }
        }
        return emailsList;
    }
}



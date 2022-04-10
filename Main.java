package ru.netoligy;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    (new Random().nextInt(100)),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long stream1 = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетние: " + stream1);


        List<String> stream2 = persons.stream()
                .filter(y -> y.getAge() >= 18)
                .filter(y -> y.getAge() < 27)
                .map(y -> (String) y.getFamily())
                .collect(Collectors.toList());
        System.out.println("Призывники: " + stream2);


        List<String> stream3 = persons.stream()
                .filter(z -> (z.getSex().equals(Sex.MAN) && z.getAge() < 65) || (z.getSex().equals(Sex.WOMAN) && z.getAge() < 60))
                .filter(z -> z.getEducation().equals(Education.HIGHER))
                .map(y -> (String) y.getFamily())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Работоспособные: " + stream3);
    }
}

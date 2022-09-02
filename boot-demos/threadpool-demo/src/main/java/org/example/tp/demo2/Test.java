package org.example.tp.demo2;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiedong
 * Date: 2022/9/2 22:12
 */
public class Test {
    public static void main(String[] args) {
        List<Person> ps = new ArrayList<>();
        Person person = new Person();
        ps.add(person);

        System.out.println();
        person.setAge(19);
        person.setName("test");
        System.out.println();
    }
}

@Data
class Person {
    private String name;
    private int age;
}

package org.example.design.template;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by dongxie on 2022/9/9.
 */
public class extendDemo {
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.setAge(12);
        cat.setName("xiao cat");
//        cat.setSex(1);

        System.out.println();
    }
}


@Data
class Animal {
    protected String name;
    protected int age;
}

@EqualsAndHashCode(callSuper = true)
@Data
class Cat extends Animal {
    private String name;
    private int age;
    private int sex;
}

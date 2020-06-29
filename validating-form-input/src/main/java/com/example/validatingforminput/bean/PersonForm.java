package com.example.validatingforminput.bean;

import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

/**
 * @author Great
 */
@Component
public class PersonForm {

    @NotEmpty
    @Size(min = 5, max = 10)
    private String name;

    @Min(18)
    @Max(100)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Form{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

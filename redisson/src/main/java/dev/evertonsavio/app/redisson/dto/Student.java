package dev.evertonsavio.app.redisson.dto;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {

    private String name;
    private int age;
    private String city;
    private List<Integer> marks;

    public Student() {
    }

    public Student(String name, int age, String city, List<Integer> marks) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.marks = marks;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMarks(List<Integer> marks) {
        this.marks = marks;
    }
}

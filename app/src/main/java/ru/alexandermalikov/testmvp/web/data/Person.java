package ru.alexandermalikov.testmvp.web.data;


public class Person {

    public static final int MALE = 0;
    public static final int FEMALE = 1;

    private long id;
    private String name;
    private int age;
    private int gender;
    private String profession;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGender() {
        return gender;
    }

    public String getProfession() {
        return profession;
    }
}

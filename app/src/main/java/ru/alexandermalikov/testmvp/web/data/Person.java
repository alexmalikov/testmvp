package ru.alexandermalikov.testmvp.web.data;


public class Person {

    public static final int MALE = 0;
    public static final int FEMALE = 1;

    private long id;
    private String name;
    private int age;
    private int gender;
    private String profession;

    public Person() {
    }

    public Person(String name, int age, int gender, String profession) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.profession = profession;
    }


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

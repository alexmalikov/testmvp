package ru.alexandermalikov.testmvp.web.data;


import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(profession);
        dest.writeInt(age);
        dest.writeInt(gender);
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    private Person(Parcel in) {
        id = in.readLong();
        name = in.readString();
        profession = in.readString();
        age = in.readInt();
        gender = in.readInt();
    }
}

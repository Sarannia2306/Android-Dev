package com.example.fitness;

public class AdminViewUserDataClass {
    private String name;
    private String age;
    private String height;
    private String weight;
    private String dob;
    private String key;
    private String gender;

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getName() { return name; }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public AdminViewUserDataClass(String name, String age, String weight, String height, String dob, String gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.dob = dob;
        this.gender = gender;
    }
    public AdminViewUserDataClass(){

    }

}
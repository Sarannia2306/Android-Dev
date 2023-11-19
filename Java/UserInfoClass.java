package com.example.fitness;

import com.google.firebase.database.FirebaseDatabase;
public class UserInfoClass {
    private String Name;
    private String Age;
    private String DOB;
    private String Weight;
    private String Height;
    private String Gender;

    // Empty constructor required by Firebase
    public UserInfoClass() {
    }

    public UserInfoClass(String Name, String Age, String DOB, String Height, String Weight, String Gender) {
        this.Name = Name;
        this.Age = Age;
        this.DOB = DOB;
        this.Height = Height;
        this.Weight = Weight;
        this.Gender = Gender;
    }

    // Getter methods with annotations

    public String getName() {
        return Name;
    }


    public String getAge() {
        return Age;
    }

    public String getDOB() {
        return DOB;
    }

    public String getWeight() {
        return Weight;
    }

    public String getHeight() {
        return Height;
    }

    public String getGender() {
        return Gender;
    }


    public void setName(String name) {
        Name = name;
    }

    // Setter methods
    public void setAge(String age) {
        Age = age;
    }

    public void setDOB(String dob) {
        DOB = dob;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}

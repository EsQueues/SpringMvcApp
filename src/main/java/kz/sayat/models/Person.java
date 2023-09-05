package kz.sayat.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    public Person(){

    }
    private int id;
    @NotEmpty(message = "Not allowed be empty!")
    @Size(min=2,max=30,message="Name have to be between 2-30 characters!")
    private String name;

    @Min(value=0,message="Age have to be greater than 0!")
    private int age;
    @NotEmpty(message = "You have to have email!")
    @Email(message = "Email should be valid!")
    private String mail;



    public Person(int id, String name, int age, String mail) {
        this.id = id;
        this.name = name;
        this.age=age;
        this.mail=mail;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
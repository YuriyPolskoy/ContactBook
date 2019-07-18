package com.polskoy.model;

import java.io.Serializable;

public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private transient String surname;
    private int age;
    private int phone;

    public Contact(String name, String surname, int age){
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public Contact(int id, String name, String surname, int age){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {return name;}

    public void setName(String name){this.name = name;}

    public String getSurname() {return surname;}

    public void setSurname(String surname){this.surname = surname;}

    public int getAge(){return age;}

    public void setAge(int age){ this.age = age; }

    public int getId(){ return id; }

    public void setId(int id) { this.id = id; }

    public String toString() { return this.id + " " + this.name + " " + this.surname + " " + this.age;}
}

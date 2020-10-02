package com.pruebadeproyecto.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Students")
public class Student {

    @Id
    private String id;
    private String name;
    private String lastname;
    private boolean attendance;

    public Student(){}
    public Student(String name, String lastname, boolean attendance){
        this.name= name;
        this.lastname=lastname;
        this.attendance=attendance;
    }

    //Getters
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getLastname(){
        return lastname;
    }
    public Boolean getAttendance(){
        return attendance;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setLastname(String lastname){
         this.lastname = lastname;
    }
    public void setAttendance(Boolean attendance){
         this.attendance = attendance;
    }

    @Override
    public String toString() {
      return "Student [id=" + id + ", nombre=" + name + ", apellido=" + lastname + ", asistencia=" + attendance + "]";
    }

}

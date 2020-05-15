package com.klientai.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="klientai")
public class Klientai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/#+=@_ ]*$")
    @Length(min=1 ,max=20,message="Prasau įvesti reikiama kiekį")
    @Column(name = "name")
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/#+=@_ ]*$")
    @Length(min=1 ,max=20,message = "Prašau įvesti reikiama kiekį")
    @Column(name = "surname")
    private String surname;
    @Column(name = "priority")
    private String priority;
    @Column(name = "commentar")
    private String commentar;

    public Klientai(int id, String name, String surname, String priority, String commentar) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.priority = priority;
        this.commentar = commentar;
    }
    public Klientai(String name, String surname, String priority, String commentar) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.priority = priority;
        this.commentar = commentar;
    }
    public Klientai() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCommentar() {
        return commentar;
    }

    public void setCommentar(String commentar) {
        this.commentar = commentar;
    }

}

package com.example.IMS_BE.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Type")
public class Type {
    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "status_name",nullable = false,length = 45)
    private String name;
    @Column(name = "status_des",length = 45)
    private String des;

    public Type() {
    }

    public Type(Long id, String name, String des) {
        this.id = id;
        this.name = name;
        this.des = des;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
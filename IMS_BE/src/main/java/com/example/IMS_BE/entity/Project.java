package com.example.IMS_BE.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private  Long id;
    @Column(name="name",length = 45)
    private String name;
    @Column(name="description",length = 45)
    private String description;
    @ManyToOne
    @JoinColumn( name="class_id",nullable = false,referencedColumnName = "class_id")
    private Classes classes;
    @ManyToOne
    @JoinColumn(name = "teamlead_id",nullable = false,referencedColumnName = "user_id")
    private User user;

    public Project() {
    }

    public Project(Long id, String name, String description, Classes classes, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.classes = classes;
        this.user = user;
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

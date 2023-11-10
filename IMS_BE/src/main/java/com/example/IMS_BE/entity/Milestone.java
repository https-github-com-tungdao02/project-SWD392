package com.example.IMS_BE.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Milestone")
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="milestone_id")
    private Long id;
    @Column(name = "name",length = 45,nullable = false)
    private String name;
    @Column(name = "description",length = 45)
    private String description;
    @Column(name = "status",length = 45)
    private String status;
    @ManyToOne
    @JoinColumn(name = "project_id",nullable = false,referencedColumnName = "project_id")
    private  Project project;
    @ManyToOne
    @JoinColumn(name = "class_id",nullable = false,referencedColumnName = "class_id")
    private  Classes classes;
    @Column(name = "start")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startdate;
    @Column(name = "end")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startend;
    public Milestone() {
    }

    public Milestone(Long id, String description, String status, String color, Project project, Classes classes, Date startdate, Date startend) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.project = project;
        this.classes = classes;
        this.startdate = startdate;
        this.startend = startend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getStartend() {
        return startend;
    }

    public void setStartend(Date startend) {
        this.startend = startend;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}

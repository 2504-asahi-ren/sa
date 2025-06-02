package com.example.sa.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String content;

    @Column
    private  int status;

    @Column(name = "limit_date")
    private Date LimitDate;

    @Column(name = "created_date",insertable = false,updatable = false)
    private  Date CreatedDate;

    @Column(name = "updated_date",insertable = false)
    private  Date UpdatedDate;
}

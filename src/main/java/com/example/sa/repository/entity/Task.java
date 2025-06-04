package com.example.sa.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
    private int status;

    @Column(name = "limit_date")
    private Date limitDate;

    @Column(name = "created_date",insertable = false,updatable = false)
    @CreatedDate //更新日時
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_date",insertable = false,updatable = false)
    @LastModifiedDate //最終更新日時
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}

package com.example.sa.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskForm {

    private int id;

    private String content;

    private  int status;

    private Date  limit_date;

    private  Date created_date;

    private  Date updated_date;

}

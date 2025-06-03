package com.example.sa.controller.form;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TaskForm {

    private int id;

    @NotBlank(message = "タスクを入力してください")
    private String content;

    private int status;

    @FutureOrPresent
    @NotBlank(message = "期限を設定してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  limitDate;


    private Date created_date;


    private Date updated_date;

}

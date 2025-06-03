package com.example.sa.controller.form;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TaskForm {

    private int id;

    @NotBlank(message = "タスクを入力してください")
    @Size(max=140, message = "タスクは140文字以内で入力してください")
    private String content;

    private int status;

    @NotBlank(message = "期限を設定してください")
    @FutureOrPresent(message = "無効な日付です")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date limitDate;

    private Date createdDate;

    private Date updatedDate;

}

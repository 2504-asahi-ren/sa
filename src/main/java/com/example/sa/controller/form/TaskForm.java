package com.example.sa.controller.form;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TaskForm {

    private int id;

    @NotBlank(message = "タスクを入力してください")
    @Pattern(regexp = "^(?!\\u3000+$)[\\s\\S]*$", message = "タスクを入力してください。")
    @Size(max=140, message = "タスクは140文字以内で入力してください")
    private String content;

    private int status;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "無効な日付です")
    @NotNull(message = "期限を設定してください")
    private Date limitDate;


//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    Date date = new Date();
//
//    @AssertTrue(message="無効な日付です")
//    public boolean isDateValid() {
//        if (limitDate != null && limitDate.before(date) || limitDate.equals(date)) {
//            return true;
//        }else {
//            return false;
//        }
//    }

    private Date createdDate;

    private Date updatedDate;

}

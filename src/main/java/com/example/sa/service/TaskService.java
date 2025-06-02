package com.example.sa.service;


import com.example.sa.controller.form.TaskForm;
import com.example.sa.repository.TaskRepository;
import com.example.sa.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    /*
     * 指定した日付のレコード全件取得処理
     */
    public List<TaskForm> findAllTask() {

        List<Task> results = taskRepository.findAll();
        List<TaskForm> tasks = setTaskForm(results);
        return tasks;
    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<TaskForm> setTaskForm(List<Task> results) {
        List<TaskForm> tasks = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TaskForm task = new TaskForm();
            Task result = results.get(i);
            task.setId(result.getId());
            task.setContent(result.getContent());
            task.setStatus(result.getStatus());
            task.setLimit_date(result.getLimitDate());
            tasks.add(task);
        }
        return tasks;
    }

}

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
    public List<TaskForm> findAllTask(String startDate,String endDate,Integer status,String content) {
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start;
        Date end;
        try {
            start = sdFormat.parse(startDate + " 00:00:00");
            end = sdFormat.parse(endDate + " 23:59:59");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Task> results = new ArrayList<>();
        if(status != null && content != null) {
            results = taskRepository.findByLimitDateBetweenAndContentContainingAndStatusOrderByLimitDateAsc(start,end,content,status);
        } else if (status == null && content != null) {
            results = taskRepository.findByLimitDateBetweenAndContentContainingOrderByLimitDateAsc(start,end,content);
        } else if (status != null && content == null) {
            results = taskRepository.findByLimitDateBetweenAndStatusOrderByLimitDateAsc(start,end,status);
        }else{
            results = taskRepository.findByLimitDateBetweenOrderByLimitDateAsc(start,end);
        }

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
            task.setLimitDate(result.getLimitDate());
            tasks.add(task);
        }
        return tasks;
    }

    /*
     * 編集内容を取得
     */
    public TaskForm editTask(Integer id){
        List<Task> results = new ArrayList<>();
        results.add((Task)taskRepository.findById(id).orElse(null));
        List<TaskForm> tasks = setTaskForm(results);
        return tasks.get(0);
    }

    /*
     * 登録・編集処理
     */
    public void saveTask(TaskForm reqTask) {
        Task saveTask = setTaskEntity(reqTask);
        taskRepository.save(saveTask);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Task setTaskEntity(TaskForm reqTask) {
        Task task = new Task();
        task.setId(reqTask.getId());
        task.setContent(reqTask.getContent());
        task.setStatus(reqTask.getStatus());
        task.setLimitDate(reqTask.getLimitDate());
        return task;
    }

    /*
     *レコード削除
     */
    public void deleteTask(Integer id) {
        Task deleteTask = deleteTaskEntity(id);
        taskRepository.delete(deleteTask);
    }
    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Task deleteTaskEntity(Integer id) {
        Task task = new Task();
        task.setId(id);
        return task;
    }

    /*
     * ステータス更新
     */
    public void changeStatus(Integer id, Integer status) {
        taskRepository.updateStatusById(id, status);
    }

}

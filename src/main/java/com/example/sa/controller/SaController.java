package com.example.sa.controller;

import com.example.sa.controller.form.TaskForm;
import com.example.sa.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class SaController {
    @Autowired
    TaskService taskService;
    /*
     * タスク表示処理
     */
    @GetMapping
    public ModelAndView top() {

        ModelAndView mav = new ModelAndView();
        // タスクを全件取得
        List<TaskForm> contentData = taskService.findAllTask();

        mav.addObject("tasks", contentData);
        mav.addObject("today", new Date());
        mav.setViewName("/top");

        return mav;
    }
}

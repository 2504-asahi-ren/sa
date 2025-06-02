package com.example.sa.controller;

import com.example.sa.controller.form.TaskForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SaController {


    /*
     * タスク表示処理
     */
    @GetMapping
    public ModelAndView top() {

        ModelAndView mav = new ModelAndView();
        // タスクを全件取得
        List<TaskForm> contentData = taskService.findAllTask();
        mav.setViewName("/top");

        return mav;
    }
}

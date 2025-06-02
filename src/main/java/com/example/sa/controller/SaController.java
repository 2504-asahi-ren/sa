package com.example.sa.controller;

import com.example.sa.controller.form.TaskForm;
import com.example.sa.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        mav.addObject("selectStatus", getSelectStatus());
        mav.setViewName("/top");

        return mav;
    }

    private Map<Integer, String> getSelectStatus(){
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "未着手");
        map.put(2, "実行中");
        map.put(3, "ステイ中");
        map.put(4, "完了");
        return map;
    }
}

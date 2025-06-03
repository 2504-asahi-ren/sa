package com.example.sa.controller;

import com.example.sa.controller.form.TaskForm;
import com.example.sa.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    /*
     * タスク追加画面表示
     */
    @GetMapping("/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        TaskForm taskForm = new TaskForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("taskForm", taskForm);
        return mav;
    }

    /*
     * 新規投稿処理
     */
    @PostMapping("/add")
    public ModelAndView addContent(@ModelAttribute("taskForm") TaskForm taskForm) {
        // 投稿をテーブルに格納

        taskService.saveTask(taskForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 投稿削除処理
     */
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable("id") Integer id) {
        // 投稿をテーブルに格納
        taskService.deleteTask(id);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
}

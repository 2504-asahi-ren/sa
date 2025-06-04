package com.example.sa.controller;

import com.example.sa.controller.form.TaskForm;
import com.example.sa.service.TaskService;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    HttpSession session;

    /*
     * タスク表示処理
     */
    @GetMapping
    public ModelAndView top() {

        ModelAndView mav = new ModelAndView();
        // タスクを全件取得
        List<TaskForm> contentData = taskService.findAllTask();

        String errorMessage = null;
        if(session.getAttribute("error") != null) {
            //errorMessage = session.getAttribute("errorMessage").toString();
            errorMessage = session.getAttribute("error").toString();
            session.invalidate();
        }
        Date date = new Date();

        mav.addObject("tasks", contentData);
        mav.addObject("today", date);
        mav.addObject("selectStatus", getSelectStatus());
        mav.addObject("error", errorMessage);
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
     * タスク追加処理
     */
    @PostMapping("/add")
    public ModelAndView addContent(@ModelAttribute("taskForm") @Validated TaskForm taskForm, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.setViewName("/new");
        }else {
            // タスクをテーブルに格納
            taskService.saveTask(taskForm);
            return new ModelAndView("redirect:/");
        }
        return mav;
    }

    /*
     * タスク編集画面表示処理
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editContent(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();

        //編集するタスクを取得
        TaskForm task = taskService.editTask(id);

//        if (task == null) {
//            session.setAttribute("error", "不正なパラメータです");
//            return new ModelAndView("redirect:/");
//        }

        //編集するタスクをセット
        mav.addObject("formModel", task);
        //画面遷移先を指定
        mav.setViewName("/edit");

        return mav;
    }

    /*
     * タスク編集処理
     */
    @PutMapping("/update/{id}")
    public ModelAndView updateContent(@PathVariable Integer id,
                                      @ModelAttribute("formModel") @Validated TaskForm task,
                                      BindingResult result) {
        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()) {
            mav.setViewName("/edit");
        } else {
            task.setId(id);
            taskService.saveTask(task);
            return new ModelAndView("redirect:/");
        }
        return mav;
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

    /*
     * ステータス変更処理
     */
    @PutMapping("/change/{id}")
    public ModelAndView changeStatus(@PathVariable Integer id,
                                     @RequestParam("status") Integer status) {
        taskService.changeStatus(id, status);
        return new ModelAndView("redirect:/");
    }
}

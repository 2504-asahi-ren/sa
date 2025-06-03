package com.example.sa.controller;

import com.example.sa.controller.form.TaskForm;
import com.example.sa.service.TaskService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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
        if(session.getAttribute("errorMessage") != null) {
            errorMessage = session.getAttribute("errorMessage").toString();
            session.invalidate();
        }

        mav.addObject("tasks", contentData);
        mav.addObject("today", new Date());
        mav.addObject("selectStatus", getSelectStatus());
        mav.addObject("errorMessage", errorMessage);
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
     * タスク編集画面表示処理
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editContent(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        //エラーメッセージを設定
        String editId = String.valueOf(id);
        if (StringUtils.isBlank(editId) || editId.matches("^[^0-9]+$")) {
            session.setAttribute("errorMessage", "不正なパラメータです");
            return new ModelAndView("redirect:/");
        }

        //編集するタスクを取得
        TaskForm task = taskService.editTask(id);

        //エラーメッセージを設定
        if(task == null) {
            session.setAttribute("errorMessage", "不正なパラメータです");
            return new ModelAndView("redirect:/");
        }
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

        if(result.hasErrors()) {
            mav.setViewName("/edit");
        } else {
            task.setId(id);
            taskService.saveTask(task);
            return new ModelAndView("redirect:/");
        }
        return mav;
    }
}

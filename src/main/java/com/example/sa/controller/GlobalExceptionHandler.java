package com.example.sa.controller;

import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @Autowired
    HttpSession session;

    @ExceptionHandler({NoResourceFoundException.class, NullPointerException.class, MethodArgumentTypeMismatchException.class})
    public String handleNotFoundException(Exception e, Model model) {
        session.setAttribute("error", "不正なパラメータです");
        return new String("redirect:/");
    }

}

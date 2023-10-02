package com.example.webprojekt.exceptions;

import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = CannotCreateTransactionException.class)
    public String exHandlerDatabase(Exception ex, Model model) {
        model.addAttribute("exception_message", "Database connection error");
        return "error";
    }

    @ExceptionHandler(value = Throwable.class)
    public String exHandlerGeneral(Throwable t, Model model) {
        model.addAttribute("exception_message", t.getMessage());
        return "error";
    }
}
package org.lastsurprise.goodgame.controller;

import org.lastsurprise.goodgame.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result globalExceptionHandler(Exception e){
        return Result.commonErrorResult(e.getMessage());
    }

}

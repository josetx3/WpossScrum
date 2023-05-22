package com.wposs.scrum_back.Exception.controller;

import com.wposs.scrum_back.Exception.dto.ErrorDto;
import com.wposs.scrum_back.Exception.exceptions.InternalServerException;
import com.wposs.scrum_back.Exception.exceptions.MessageGeneric;
import com.wposs.scrum_back.Exception.exceptions.MethodArgumentNotValidException;
import com.wposs.scrum_back.Exception.exceptions.RequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(value = InternalServerException.class)
    public ResponseEntity<ErrorDto> internalError(InternalServerException ex){
        ErrorDto errorDto = new ErrorDto(ex.getCode(),ex.getMessage());
        return new ResponseEntity<>(errorDto,ex.getStatus());
    }

    @ExceptionHandler(value = MessageGeneric.class)
    public ResponseEntity<ErrorDto> messageGeneric(MessageGeneric ex){
        ErrorDto errorDto = new ErrorDto(ex.getCode(),ex.getMessage());
        return new ResponseEntity<>(errorDto,ex.getStatus());
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorDto> badRequest(RequestException ex){
        ErrorDto errorDto = new ErrorDto(ex.getCode(),ex.getMessage());
        return new ResponseEntity<>(errorDto,ex.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> validationData(MethodArgumentNotValidException ex){
        ErrorDto errorDto = new ErrorDto(ex.getMessage(),ex.getCode());
        return new ResponseEntity<>(errorDto,ex.getStatus());
    }
}

package com.interpark.ncl.config;

import javassist.NotFoundException;
import net.minidev.json.JSONObject;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@EnableWebMvc
@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler({ServletRequestBindingException.class})
    public ResponseEntity<Object> ServletRequestBindingException(Exception ex, WebRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "필수 파라메터가 누락되었습니다.");
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", "application/json; charset=utf-8");
        return new ResponseEntity<Object>(jsonObject, header, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> ConstraintViolationException(Exception ex, WebRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "필수 파라메터가 누락되었습니다.");
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", "application/json; charset=utf-8");
        return new ResponseEntity<Object>(jsonObject, header, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class, NotFoundException.class, NoSuchElementException.class, NoHandlerFoundException.class})
    public ResponseEntity<Object> ResourceNotFoundException(Exception ex, WebRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "요청한 리소스가 존재하지 않습니다.");
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", "application/json; charset=utf-8");
        return new ResponseEntity<Object>(jsonObject, header, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> InvalidPropertyException(Exception ex, WebRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "요청을 실패하였습니다.");
        HttpHeaders header = new HttpHeaders();
        header.add("Content-type", "application/json; charset=utf-8");
        return new ResponseEntity<Object>(jsonObject, header, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

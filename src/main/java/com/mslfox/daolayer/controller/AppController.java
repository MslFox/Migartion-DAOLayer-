package com.mslfox.daolayer.controller;

import com.mslfox.daolayer.service.AppService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
    private final AppService service;

    public AppController(AppService service) {
        this.service = service;
    }

    @GetMapping("/products/fetch-product")
    public ResponseEntity<String> transfer(@RequestParam String name) {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(service.getProductNameByName(name));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> exceptionName() {
        return ResponseEntity.ok("Введите корректно имя заказчика в строке запроса по шаблону ('http://localhost:8080/products/fetch-product?name=')");
    }
}

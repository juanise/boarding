package com.pf.boarding.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/reactive")
public class testService {

    @GetMapping
    public String testServiceString() {
        return "Esta es una APP reactive desarrollada con mongo";
    }
}
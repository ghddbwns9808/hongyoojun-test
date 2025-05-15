package io.github.shinhancard.fluxocisample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        for (int i = 0; i < 10; i++) {  
            new Thread(() -> burnCpu(10000)).start(); 
        }
        return "hello spring!!S";
    }

    private void burnCpu(long milliseconds) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < milliseconds) {
            Math.pow(Math.random(), Math.random()); // CPU를 소모하는 무의미한 계산
        }
    }
}
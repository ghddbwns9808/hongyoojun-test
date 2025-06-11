package io.github.shinhancard.fluxocisample.controller;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final AtomicBoolean burning = new AtomicBoolean(false);
    private Thread burnThread;

    @GetMapping("/")
    public String hello() {
        return "hello yoojun!!";
    }

    @GetMapping("/cpustart")
    public String startBurning() {
        if (burning.get()) {
            return "CPU burning is already running.";
        }

        burning.set(true);
        burnThread = new Thread(() -> {
            while (burning.get()) {
                Math.pow(Math.random(), Math.random());
            }
        });

        burnThread.start();
        return "Started CPU burning.";
    }

    @GetMapping("/cpuend")
    public String stopBurning() {
        if (!burning.get()) {
            return "CPU burning is not running.";
        }

        burning.set(false);
        try {
            burnThread.join(1000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Stopped CPU burning.";
    }
}
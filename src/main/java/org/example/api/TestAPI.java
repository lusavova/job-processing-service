package org.example.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestAPI {

    @GetMapping("/test")
    public String testApi() {
        System.out.println("TEST");
        return "testing if api is working";
    }
}

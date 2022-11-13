package org.coverage_demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server {
    private static final int THREE = 3;

    /**
     * Delegate to backend ("DB"), what most servers do.
     * This is the second layer of dependency.
     */
    @GetMapping
    public int two() {
        return new DB().two();
    }

    int three() {
        return THREE;
    }

    @GetMapping("four")
    public int four() {
        return new DB().four();
    }
}

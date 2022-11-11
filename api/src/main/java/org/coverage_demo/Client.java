package org.coverage_demo;

import java.util.Objects;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestOperations;

public class Client {
    private final RestOperations restOperations = new RestTemplateBuilder().build();
    private final int port;
    private static final int THREE = 3;

    public Client(int port) {
        this.port = port;
    }

    /**
     * Delegate to backend, what most APIs do.
     * This is the first layer of dependency.
     */
    public int two() {
        return Objects.requireNonNull(
                restOperations.getForObject("http://localhost:%d".formatted(port), Integer.class)
        );
    }

    int three() {
        return THREE;
    }
}

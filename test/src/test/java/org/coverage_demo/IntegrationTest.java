package org.coverage_demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {
    @LocalServerPort
    private int port;

    /**
     * Call API and make sure real backend is called for transitive coverage.
     */
    @Test
    @DisplayName("Call API and make sure real backend is called for transitive coverage.")
    void callApiMethod() {
        assertThat(new Client(port).two()).isEqualTo(2);
    }

    @Test
    @DisplayName("Call /foure and make sure real backend is called for transitive coverage.")
    void callAnotherApiMethod() {
        assertThat(new Client(port).four()).isEqualTo(4);
    }
}

package org.coverage_demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ServerTest {
    @Test
    void nonApiMethod() {
        assertThat(new Server().three()).isEqualTo(3);
    }
}

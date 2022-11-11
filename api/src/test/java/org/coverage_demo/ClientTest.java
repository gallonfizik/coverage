package org.coverage_demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClientTest {
    /**
     * Provide local coverage for a method not exposed in the API.
     */
    @Test
    void thriceLocal() {
        assertThat(new Client(0).three()).isEqualTo(3);
    }
}

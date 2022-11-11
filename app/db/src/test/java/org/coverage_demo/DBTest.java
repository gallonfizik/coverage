package org.coverage_demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DBTest {
    @Test
    void dbNonApiMethod() {
        assertThat(new DB().three()).isEqualTo(3);
    }
}

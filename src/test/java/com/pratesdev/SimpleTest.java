package com.pratesdev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleTest {
    @Test
    void simpleAdditionTest() {
        int sum = 2 + 3;
        assertEquals(5, sum, "2 + 3 deve ser igual a 5");
    }
}

package org.tspp.ed_process.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GradeTest {


    @Test
    public void testCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            var g = new Grade(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            var g = new Grade(101);
        });

        assertDoesNotThrow(() -> {
            new Grade(50);
        });
    }

    @Test
    public void testGetValue() {
        // The given to constructor value is always equal to return value of getValue().
        var values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (var i : values) {
            var g = new Grade(i);
            assertEquals(i, g.get());
        }
    }

    @Test
    public void testIsSatisfactory() {

        var g0 = new Grade(0);
        assertFalse(g0.isSatisfactory());

        var g1 = new Grade(55);
        assertFalse(g1.isSatisfactory());

        var g2 = new Grade(60);
        assertTrue(g2.isSatisfactory());

        var g3 = new Grade(85);
        assertTrue(g3.isSatisfactory());

        var g4 = new Grade(100);
        assertTrue(g4.isSatisfactory());

    }
}

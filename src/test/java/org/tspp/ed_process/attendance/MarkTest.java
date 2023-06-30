package org.tspp.ed_process.attendance;


import org.junit.Test;
import org.tspp.users.Student;

import static org.junit.jupiter.api.Assertions.*;

public class MarkTest {

    private final static Student student = new Student(
            "John",
            "John",
            "Doe",
            "jjdoe@email.com",
            "very_strong_and_complicated_password"
    );

    @Test
    public void testGetValue() {
        Mark mark = new Mark(student, Mark.Value.ATTENDED);
        assertEquals(Mark.Value.ATTENDED, mark.getValue());
    }

    @Test
    public void testGetStudent() {
        Mark mark = new Mark(student, Mark.Value.ATTENDED);
        assertEquals(student, mark.getStudent());
    }

    @Test
    public void testIsAttended() {
        Mark mark = new Mark(student, Mark.Value.ATTENDED);
        assertTrue(mark.isAttended());
        assertFalse(mark.isLate());
        assertFalse(mark.isNotPresented());
    }

    @Test
    public void testIsLate() {
        Mark mark = new Mark(student, Mark.Value.LATE);
        assertTrue(mark.isLate());
        assertFalse(mark.isAttended());
        assertFalse(mark.isNotPresented());
    }

    @Test
    public void testIsNotPresented() {
        Mark mark = new Mark(student, Mark.Value.NOT_PRESENTED);
        assertTrue(mark.isNotPresented());
        assertFalse(mark.isAttended());
        assertFalse(mark.isLate());
    }
}
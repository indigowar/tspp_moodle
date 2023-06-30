package org.tspp.ed_process;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.tspp.users.Student;

public class GroupTest {
    private final Group group;

    private final Student student1;
    private final Student student2;

    @Test
    void testGetName() {
        assertEquals("Test Group", group.getName());
    }

    @Test
    void testHasStudent_NoStudents_ReturnsFalse() {
        assertFalse(group.hasStudent(student1));
    }

    @Test
    void testHasStudent_StudentNotInGroup_ReturnsFalse() {
        group.addStudent(student1);
        assertFalse(group.hasStudent(student2));
    }

    @Test
    void testHasStudent_StudentInGroup_ReturnsTrue() {
        group.addStudent(student1);
        assertTrue(group.hasStudent(student1));
    }

    @Test
    void testAddStudent_StudentAlreadyAdded_ThrowsRuntimeException() {
        group.addStudent(student1);

        assertThrows(RuntimeException.class, () -> {
            group.addStudent(student1);
        });
    }

    @Test
    void testAddStudent_StudentJoinGroup() {
        group.addStudent(student1);

        assertTrue(group.hasStudent(student1));
    }

    @Test
    void testRemoveStudent_StudentNotInGroup_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            group.removeStudent(student1);
        });
    }

    @Test
    void testRemoveStudent_StudentLeaveGroup() {
        group.addStudent(student1);
        assertTrue(group.hasStudent(student1));
        group.removeStudent(student1);

        assertFalse(group.hasStudent(student1));
    }
}

package org.tspp.ed_process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.tspp.users.Student;

public class GroupTest {
    private Group group;

    private Student student1;
    private Student student2;


    @BeforeEach
    void setUp() {
        group = new Group("Test Group");
        student1 = new Student("John", "John", "Doe", "jjdoe@gmail.com", "password");
        student2 = new Student("Jane", "Jenny", "Smith", "jjsmith@gmail.com", "password");
    }


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

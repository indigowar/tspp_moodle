package org.tspp.ed_process.attendance;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tspp.ed_process.Group;
import org.tspp.ed_process.Subject;
import org.tspp.users.Student;
import org.tspp.users.Teacher;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;


class LessonTest {

    private Teacher teacher;
    private Student student;
    private Group group;
    private Subject subject;
    private Lesson lesson;


    @BeforeEach
    void setUp() {
        student = new Student("John", "John", "Doe", "jjdoe@gmail.com", "password");
        teacher = null;

        group = new Group("test group");
        group.addStudent(this.student);

        subject = new Subject("Test Subject", teacher);
        subject.addGroup(group);

        lesson = new Lesson(subject, Lesson.Type.LECTURE, "Test", LocalDateTime.now());
    }

    @Test
    public void testGetType() {
        assertEquals(lesson.getType(), Lesson.Type.LECTURE);
    }

    @Test
    public void testGetName() {
        assertEquals(lesson.getTitle(), "Test");
    }

    @Test
    public void testGetSubject() {
        assertEquals(lesson.getSubject(), subject);
    }

    @Test
    public void testHasMark_NoMarkForStudent() {
        assertFalse(lesson.hasMark(student));
    }

    @Test
    public void testHasMark_StudentHasMark() {
        lesson.setAttendance(student, Mark.Value.ATTENDED);
        assertTrue(lesson.hasMark(student));
    }

    @Test
    public void testGetStudentsMark_MarkExists_ReturnsMark() {
        lesson.setAttendance(student, Mark.Value.ATTENDED);
        Mark mark = lesson.getStudentMark(student);

        assertNotNull(mark);
        assertEquals(student, mark.getStudent());
        assertEquals(Mark.Value.ATTENDED, mark.getValue());
    }

    @Test
    public void testGetStudentsMark_MarkDoesNotExist_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            lesson.getStudentMark(student);
        });
    }

    @Test
    public void testSetAttendance_StudentHasMark_ThrowsRuntimeException() {
        lesson.setAttendance(student, Mark.Value.ATTENDED);

        assertThrows(RuntimeException.class, () -> {
            lesson.setAttendance(student, Mark.Value.LATE);
        });
    }

    @Test
    public void testGetMarks() {
        lesson.setAttendance(student, Mark.Value.ATTENDED);
        ArrayList<Mark> marks = lesson.getMarks();

        assertEquals(1, marks.size());
        assertEquals(student, marks.get(0).getStudent());
        assertEquals(Mark.Value.ATTENDED, marks.get(0).getValue());
    }
}
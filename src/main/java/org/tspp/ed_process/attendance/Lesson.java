package org.tspp.ed_process.attendance;

import org.tspp.ed_process.Subject;
import org.tspp.users.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;

public final class Lesson {
    public enum Type {
        LABORATORY, PRACTICAL, LECTURE
    }

    public Lesson(Subject s, Type t, String n, LocalDateTime time) {
    }

    public final Type getType() {
        return null;
    }

    public final String getTitle() {
        return "";
    }

    public final Subject getSubject() {
        return null;
    }

    public final LocalDateTime getTime() {
        return null;
    }

    public boolean hasMark(Student s) {
        return false;
    }

    public final Mark getStudentMark(Student s) throws RuntimeException {
        return null;
    }


    void setAttendance(Student s, Mark.Value value) throws RuntimeException {
    }

    public final ArrayList<Mark> getMarks() {
        return new ArrayList<>();
    }
}

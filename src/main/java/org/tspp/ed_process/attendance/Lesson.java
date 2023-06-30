package org.tspp.ed_process.attendance;

import org.tspp.ed_process.Subject;
import org.tspp.users.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Lesson {
    public enum Type {
        LABORATORY, PRACTICAL, LECTURE
    }

    public Lesson(Subject s, Type t, String n, LocalDateTime time) {
        this.subject = s;
        this.type = t;
        this.title = n;
        this.time = time;

        this.marks = new ArrayList<>();
    }

    public final Type getType() {
        return this.type;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Subject getSubject() {
        return this.subject;
    }

    public final LocalDateTime getTime() {
        return this.time;
    }

    public boolean hasMark(Student s) {
        return marks.stream().anyMatch(i -> Objects.equals(i.getStudent(), s));
    }

    public final Mark getStudentMark(Student s) throws RuntimeException {
        var result = marks.stream().filter(i -> Objects.equals(i.getStudent(), s)).findFirst();
        if (result.isEmpty()) {
            throw new RuntimeException("student has no mark in that lesson");
        }
        return result.get();
    }


    public void setAttendance(Student s, Mark.Value value) throws RuntimeException {
        if (hasMark(s)) {
            throw new RuntimeException("student already has a mark for this lesson");
        }
        marks.add(new Mark(s, value));
    }

    public final ArrayList<Mark> getMarks() {
        return marks;
    }


    private final Type type;
    private final String title;
    private final ArrayList<Mark> marks;

    private final Subject subject;
    private final LocalDateTime time;

}

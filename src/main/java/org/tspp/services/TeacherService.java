package org.tspp.services;

import org.tspp.ed_process.Subject;
import org.tspp.ed_process.attendance.Lesson;
import org.tspp.ed_process.attendance.Mark;
import org.tspp.ed_process.tasks.*;
import org.tspp.users.Student;
import org.tspp.users.Teacher;

import java.util.ArrayList;
import java.util.Arrays;

public class TeacherService {
    public TeacherService(Storages storages, Teacher teacher) {
        this.storages = storages;
        this.teacher = teacher;
    }


    public final ArrayList<Subject> getSubjects() {
        return teacher.getSubjects();
    }


    public final Subject creatSubject(String title) {
        var s = teacher.createSubject(title);
        storages.getSubjects().add(s);
        return s;
    }

    public final ArrayList<Task> getPendingTasks(Subject subject) {
        return new ArrayList<>(teacher.getPendingTasks(subject).toList());
    }

    public final void gradeAttempt(Attempt a, int value) {
        a.grade(new Grade(value));
    }

    public void setStudentAttendance(Lesson lesson, Student student, Mark.Value value) {
        this.teacher.setStudentsAttendance(lesson, student, value);
    }

    public Task createOnlyTextTask(Subject subject, Task.Type type, String title, int limitOfAttempts) {
        Requirement req = new TextRequirement(true);
        return createTaskWithCustomRequirements(subject, type, title, req, limitOfAttempts);
    }

    public Task createTaskWithFileRequirement(Subject subject, Task.Type type, String title, int limitOfAttempts, ArrayList<String> extensions, boolean hasText) {
        Requirement text_req = new TextRequirement(hasText);
        Requirement file_req = new FileRequirementByExtension(extensions);
        Requirement req = new MultipleRequirement(new ArrayList<>(Arrays.asList(text_req, file_req)));
        return createTaskWithCustomRequirements(subject, type, title, req, limitOfAttempts);
    }

    public Task createTaskWithCustomRequirements(Subject subject, Task.Type type, String title, Requirement req, int limitOfAttempts) {
        return this.teacher.createTask(subject, type, title, req, limitOfAttempts);
    }

    private final Storages storages;
    private final Teacher teacher;
}

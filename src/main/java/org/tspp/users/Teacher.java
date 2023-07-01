package org.tspp.users;

import org.tspp.ed_process.Subject;
import org.tspp.ed_process.attendance.Lesson;
import org.tspp.ed_process.attendance.Mark;
import org.tspp.ed_process.tasks.Requirement;
import org.tspp.ed_process.tasks.Task;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Teacher extends User {
    public Teacher(String name, String patronymic, String surname, String mail, String password) {
        super(name, patronymic, surname, mail, password);

        subjects = new ArrayList<>();
    }

    public Subject createSubject(String title) {
        var s = new Subject(title, this);
        subjects.add(s);
        return s;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public ArrayList<Task> getTasksWithPendingSubmissions() {
        var tasks = new ArrayList<Task>();
        subjects.forEach(s -> {
            getPendingTasks(s).forEach(tasks::add);
        });
        return tasks;
    }

    public ArrayList<Task> getPendingTasksForSubject(Subject subject) {
        if (!subjects.contains(subject)) {
            throw new RuntimeException("this teacher does not have permission for this subject");
        }
        var tasks = new ArrayList<Task>();
        getPendingTasks(subject).forEach(tasks::add);
        return tasks;
    }

    public void setStudentsAttendance(Lesson lesson, Student student, Mark.Value value) {
        lesson.setAttendance(student, value);
    }

    public Task createTask(Subject subject, Task.Type type, String title, Requirement req, int limitOfAttempts) {
        if (!subjects.contains(subject)) {
            throw new IllegalArgumentException("this subject is not managed by this teacher");
        }
        return subject.createTask(this, type, title, req, limitOfAttempts);
    }

    public Stream<Task> getPendingTasks(Subject subject) {
        return subject.getTasks()
                .stream()
                .filter(Task::hasPendingSubmissions);
    }

    private final ArrayList<Subject> subjects;
}

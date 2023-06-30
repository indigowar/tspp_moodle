package org.tspp.ed_process.tasks;

import org.tspp.ed_process.Subject;
import org.tspp.users.Student;

import java.util.HashMap;
import java.util.Objects;

public class Task {
    public enum Type {
        LABORATORY, RGR, TEST, EXAM
    }


    public Task(Type type, String name, Subject subject, Requirement req, int limit) {
        this.type = type;
        this.requirement = req;
        this.submissions = new HashMap<>();
        this.limit = limit;
        this.name = name;
        this.subject = subject;
    }

    public final String getName() {
        return name;
    }

    public final Type getType() {
        return type;
    }

    public void submit(Student student, Attempt attempt) {
        if (!requirement.isSatisfiedBy(attempt)) {
            throw new IllegalArgumentException("the attempt does not satisfy the requirements");
        }
//        if (!student.hasSubject(subject)) {
//            throw new IllegalArgumentException("this student can not send submissions on this task");
//        }
        if (!submissions.containsKey(student)) {
            submissions.put(student, new Submission(limit));
        }
        var s = submissions.get(student);
        s.addAttempt(attempt);
    }

    public Submission getSubmissionForStudent(Student student) {
        if (!submissions.containsKey(student)) {
            throw new RuntimeException("submission for given student does not exists");
        }
        return submissions.get(student);
    }

    public Submission getPendingSubmission() {
        for (var submission : submissions.values()) {
            if (submission.hasUncheckedAttempts()) {
                return submission;
            }
        }
        throw new RuntimeException("task has no pending submissions");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return limit == task.limit && type == task.type && Objects.equals(name, task.name) && Objects.equals(subject, task.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, type, name, subject);
    }

    private final int limit;
    private final Type type;
    private final Requirement requirement;
    private final HashMap<Student, Submission> submissions;
    private final String name;
    private final Subject subject;
}

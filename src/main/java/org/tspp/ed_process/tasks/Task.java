package org.tspp.ed_process.tasks;

import org.tspp.ed_process.Subject;
import org.tspp.users.Student;

import java.util.Objects;

public class Task {
    public enum Type {
        LABORATORY,
        RGR,
        TEST,
        EXAM
    }

    public Task(Type type, String title, Subject subject, Requirement requirement, int limit) {
    }


    public final String getName() {
        return "";
    }

    public final Type getType() {
        return null;
    }

    public void submit(Student student, Attempt attempt) throws IllegalArgumentException {
    }

    public Submission getSubmissionForStudent(Student student) throws RuntimeException {
        return null;
    }

    public Submission getPendingSubmission() throws RuntimeException {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}

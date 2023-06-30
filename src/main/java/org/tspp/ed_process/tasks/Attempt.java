package org.tspp.ed_process.tasks;

import java.util.ArrayList;
import java.util.Optional;

public class Attempt {
    public Attempt(ArrayList<String> files) {
        this.text = "";
        this.grade = Optional.empty();

        this.files = files;
    }

    public Attempt(String text) {
        this.text = text;
        this.grade = Optional.empty();

        this.files = new ArrayList<>();
    }

    public Attempt(String text, ArrayList<String> files) {
        this.text = text;
        this.files = files;
        this.grade = Optional.empty();
    }

    public final boolean isGraded() {
        return this.grade.isPresent();
    }

    public void grade(Grade g) throws RuntimeException {
        if (isGraded()) {
            throw new RuntimeException("this attempt is already graded!");
        }
        grade = Optional.of(g);
    }

    public Grade getGrade() {
        if (grade.isEmpty()) {
            throw new RuntimeException("this attmept is not graded!");
        }
        return grade.get();
    }

    public final boolean hasFiles() {
        return files.size() > 0;
    }

    public final boolean hasText() {
        return text.length() > 0;
    }

    public final String getText() {
        return text;
    }

    public final ArrayList<String> getFiles() {
        return files;
    }


    private Optional<Grade> grade;

    private final ArrayList<String> files;
    private final String text;
}

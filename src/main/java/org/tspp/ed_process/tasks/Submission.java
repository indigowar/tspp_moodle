package org.tspp.ed_process.tasks;

import java.util.ArrayList;

public class Submission {
    public Submission(int limit) throws IllegalArgumentException {
        if (limit <= 0) {
            throw new IllegalArgumentException("limit should be greater then zero");
        }
        this.limit = limit;
        this.attempts = new ArrayList<>();
    }

    public final int getLimit() {
        return this.limit;
    }

    public void addAttempt(Attempt attempt) throws RuntimeException, IllegalArgumentException {
        if (attempt.isGraded()) {
            throw new IllegalArgumentException("graded attempt cannot be added to submission");
        }

        if (attempts.size() + 1 > limit) {
            throw new RuntimeException("submission is full of attempts");
        }

        attempts.add(attempt);
    }

    public final boolean hasUncheckedAttempts() {
        for (final var a : attempts) {
            if (!a.isGraded()) {
                return true;
            }
        }
        return false;
    }

    public Attempt getUnchecked() throws RuntimeException {
        for (var a : attempts) {
            if (!a.isGraded()) {
                return a;
            }
        }
        throw new RuntimeException("this submission has no unchecked attempts");
    }

    public final Grade getMaxGrade() {
        var max_value = new Grade(0);

        for (final var a : attempts) {
            if (a.isGraded()) {
                var value = a.getGrade();
                if (value.get() > max_value.get()) {
                    max_value = value;
                }
            }
        }
        return max_value;
    }

    public final boolean isSuccessfullyDone() {
        final var max = getMaxGrade();
        return max.isSatisfactory();
    }


    private final int limit;
    private final ArrayList<Attempt> attempts;
}

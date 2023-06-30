package org.tspp.ed_process.tasks;

public class Submission {
    public Submission(int limit) {
    }

    public final int getLimit() {
        return 0;
    }

    public void addAttempt(Attempt attempt) throws RuntimeException {
    }

    public final boolean hasUncheckedAttempts() {
        return false;
    }

    public Attempt getUnchecked() {
        return null;
    }

    public final Grade getMaxGrade() {
        return null;
    }

    public final boolean isSuccessfullyDone() {
        return false;
    }
}

package org.tspp.ed_process.tasks;

public class TextRequirement implements Requirement {
    public TextRequirement(boolean hasText) {
        this.hasText = hasText;
    }

    @Override
    public boolean isSatisfiedBy(Attempt a) {
        if (this.hasText) {
            return a.hasText();
        }
        return !a.hasText();
    }

    private final boolean hasText;
}

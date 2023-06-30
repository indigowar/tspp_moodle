package org.tspp.ed_process.tasks;

public interface Requirement {
    public boolean isSatisfiedBy(Attempt a);
}

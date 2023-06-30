package org.tspp.ed_process.tasks;

import java.util.ArrayList;

public class MultipleRequirement implements Requirement {
    public MultipleRequirement(ArrayList<Requirement> requirements) {
        this.requirements = requirements;
    }


    @Override
    public boolean isSatisfiedBy(Attempt a) {
        for (final var req : requirements) {
            if (!req.isSatisfiedBy(a)) {
                return false;
            }
        }
        return true;
    }

    private final ArrayList<Requirement> requirements;

}

package org.tspp.ed_process.tasks;


import java.util.ArrayList;

public class FileRequirementByExtension implements Requirement {
    public FileRequirementByExtension(final ArrayList<String> extensions) {
        this.extensions = extensions;
    }

    @Override
    public final boolean isSatisfiedBy(final Attempt a) {
        if (extensions.isEmpty() && a.hasFiles()) {
            return true;
        }

        if (!extensions.isEmpty() && !a.hasFiles()) {
            return false;
        }

        for (final var filename : a.getFiles()) {
            boolean hasExt = false;
            for (final var extension : extensions) {
                if (filename.endsWith(extension)) {
                    hasExt = true;
                    break;
                }
            }
            if (!hasExt) {
                return false;
            }
        }
        return true;
    }


    private final ArrayList<String> extensions;
}

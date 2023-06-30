package org.tspp.ed_process.tasks;

import java.util.Objects;

public class Grade {


    public Grade(int value) throws IllegalArgumentException {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("grade value should be between 0 and 100");
        }
        this.value = value;
    }

    public final int get() {
        return this.value;
    }

    public final boolean isSatisfactory() {
        return this.value >= 60;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Grade g)) return false;
        return this.value == g.get();
    }


    private final int value;
}

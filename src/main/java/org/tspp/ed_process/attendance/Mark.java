package org.tspp.ed_process.attendance;

import org.tspp.users.Student;

public final class Mark {
    public enum Value {
        ATTENDED,
        LATE,
        NOT_PRESENTED
    }

    public Mark(Student s, Value v) {
        this.student = s;
        this.value = v;
    }

    public final Value getValue() {
        return this.value;
    }

    public final Student getStudent() {
        return this.student;
    }

    public final boolean isAttended() {
        return this.value == Value.ATTENDED;
    }

    public final boolean isLate() {
        return this.value == Value.LATE;
    }

    public final boolean isNotPresented() {
        return this.value == Value.NOT_PRESENTED;
    }

    private final Student student;
    private final Value value;
}

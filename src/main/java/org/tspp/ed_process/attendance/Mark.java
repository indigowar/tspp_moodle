package org.tspp.ed_process.attendance;

import org.tspp.users.Student;

public final class Mark {
    public enum Value {
        ATTENDED,
        LATE,
        NOT_PRESENTED
    }

    Mark(Student s, Value v) {
    }

    public final Value getValue() {
        return null;
    }

    public final Student getStudent() {
        return null;
    }

    public final boolean isAttended() {
        return false;
    }

    public final boolean isLate() {
        return false;
    }

    public final boolean isNotPresented() {
        return false;
    }
}

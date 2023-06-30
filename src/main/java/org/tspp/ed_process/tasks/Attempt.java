package org.tspp.ed_process.tasks;

import java.util.ArrayList;

public class Attempt {
    public Attempt(ArrayList<String> files) {
    }

    public Attempt(String text) {
    }

    public Attempt(String text, ArrayList<String> files) {
    }

    public final boolean isGraded() {
        return false;
    }

    public void grade(Grade g) {
    }

    public Grade getGrade() {
        return null;
    }

    public final boolean hasFiles() {
        return false;
    }

    public final boolean hasText() {
        return false;
    }

    public final String getText() {
        return "";
    }

    public final ArrayList<String> getFiles() {
        return null;
    }


}

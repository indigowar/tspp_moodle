package org.tspp.ed_process;

import org.tspp.users.Student;

import java.util.ArrayList;
import java.util.Objects;

public final class Group {

    public Group(String name) {
        this.name = name;
        this.students = new ArrayList<>();

        this.subjects = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public boolean hasStudent(Student s) {
        return students.stream().anyMatch(i -> Objects.equals(i, s));
    }

    public void addStudent(Student s) throws RuntimeException {
        if (hasStudent(s)) {
            throw new RuntimeException("student is in the group already");
        }
        students.add(s);
    }

    public void removeStudent(Student s) throws RuntimeException {
        // if the value was found in the array, has_value =  true
        boolean has_value = students.remove(s);

        if (!has_value) {
            throw new RuntimeException("student was not found in the group");
        }
    }

    public final ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public final boolean hasSubject(Subject s) {
        return subjects.stream().anyMatch(i -> Objects.equals(i, s));
    }

    public final void addSubject(Subject s) {
        subjects.add(s);
    }

    public final void removeSubject(Subject s) {
        subjects.remove(s);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Group g)) {
            return false;
        }
        return Objects.equals(getName(), g.getName());
    }


    private final String name;
    private final ArrayList<Student> students;
    private final ArrayList<Subject> subjects;
}

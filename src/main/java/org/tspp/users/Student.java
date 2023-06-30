package org.tspp.users;

import org.tspp.ed_process.Group;
import org.tspp.ed_process.Subject;

import java.util.ArrayList;
import java.util.Objects;

public class Student extends User {
    public Student(String name, String patronymic, String surname, String mail, String password) {
        super(name, patronymic, surname, mail, password);
        this.groups = new ArrayList<>();
    }

    public void joinGroup(Group g) throws RuntimeException {
        if (isInGroup(g)) {
            throw new RuntimeException("Student is already in this group");
        }
        groups.add(g);
    }

    public void leaveGroup(Group g) throws RuntimeException {
        if (!isInGroup(g)) {
            throw new RuntimeException("Student is not in this group");
        }
        groups.remove(g);
    }

    public boolean isInGroup(Group g) {
        return groups.stream().anyMatch(i -> i.hasStudent(this) && Objects.equals(i, g));
    }

    public final boolean hasSubject(Subject s) {
        return groups.stream().anyMatch(i -> i.hasSubject(s));
    }


    private final ArrayList<Group> groups;
}

package org.tspp.ed_process;

import org.tspp.users.Teacher;

import java.util.ArrayList;
import java.util.Objects;

public final class Subject {

    public Subject(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;

        this.groups = new ArrayList<>();
    }

    public final String getTitle() {
        return this.title;
    }

    public void addGroup(Group g) throws IllegalArgumentException {
        if (hasGroup(g)) {
            throw new IllegalArgumentException("this group is already has this subject");
        }
        groups.add(g);
    }

    public void removeGroup(Group g) throws IllegalArgumentException {
        if (!hasGroup(g)) {
            throw new IllegalArgumentException("this group does not have this subject");
        }
        groups.remove(g);
    }

    public boolean hasGroup(Group g) {
        return groups.stream().anyMatch(i -> Objects.equals(i, g));
    }


    // TODO: createTask()
    // TODO: createLesson()
    // TODO: getTasks()
    // TODO: getLessons()


    private final String title;
    private final Teacher teacher;

    private final ArrayList<Group> groups;


}

package org.tspp.services;

import org.tspp.ed_process.Group;
import org.tspp.ed_process.Subject;
import org.tspp.users.Student;
import org.tspp.users.Teacher;

import java.util.ArrayList;

/**
 * TLDR: I know it's ugly, but it does simplify project.
 * Every single field here supposed to be a storage(repository) interface.
 */
public final class Storages {

    public Storages() {
        this.subjects = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.students = new ArrayList<>();
    }


    public final ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public final ArrayList<Student> getStudents() {
        return students;
    }

    public final ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public final ArrayList<Group> getGroups() {
        return groups;
    }


    private final ArrayList<Subject> subjects;
    private final ArrayList<Student> students;
    private final ArrayList<Teacher> teachers;

    private final ArrayList<Group> groups;
}

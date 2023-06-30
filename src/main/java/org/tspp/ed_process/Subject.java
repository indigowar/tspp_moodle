package org.tspp.ed_process;

import org.tspp.ed_process.attendance.Lesson;
import org.tspp.ed_process.tasks.Requirement;
import org.tspp.ed_process.tasks.Task;
import org.tspp.users.Teacher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public final class Subject {

    public Subject(String title, Teacher teacher) {
        this.title = title;
        this.teacher = teacher;

        this.groups = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public final String getTitle() {
        return this.title;
    }

    public void addGroup(Group g) throws IllegalArgumentException {
        if (hasGroup(g)) {
            throw new IllegalArgumentException("this group is already has this subject");
        }
        groups.add(g);
        g.addSubject(this);
    }

    public void removeGroup(Group g) throws IllegalArgumentException {
        if (!hasGroup(g)) {
            throw new IllegalArgumentException("this group does not have this subject");
        }
        groups.remove(g);
        g.removeSubject(this);
    }

    public boolean hasGroup(Group g) {
        return groups.stream().anyMatch(i -> Objects.equals(i, g));
    }

    public Task createTask(Teacher teacher, Task.Type type, String name, Requirement req, int limit) throws RuntimeException {
        if (Objects.equals(this.teacher, teacher)) {
            throw new SecurityException("this teacher does not have permissions to manage this subject");
        }
        var task = new Task(type, name, this, req, limit);
        tasks.add(task);
        return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Lesson createLesson(Teacher teacher, Lesson.Type type, String title, LocalDateTime time) {
        if (Objects.equals(this.teacher, teacher)) {
            throw new SecurityException("this teacher does not have permissions to manage this subject");
        }
        var lesson = new Lesson(this, type, title, time);
        lessons.add(lesson);
        return lesson;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    private final String title;
    private final Teacher teacher;

    private final ArrayList<Group> groups;


    private final ArrayList<Task> tasks;
    private final ArrayList<Lesson> lessons;
}

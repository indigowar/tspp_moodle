package org.tspp.services;

import org.tspp.ed_process.Group;
import org.tspp.ed_process.Subject;
import org.tspp.ed_process.attendance.Lesson;
import org.tspp.ed_process.attendance.Mark;
import org.tspp.ed_process.tasks.Attempt;
import org.tspp.ed_process.tasks.Grade;
import org.tspp.ed_process.tasks.Task;
import org.tspp.users.Student;

import java.util.ArrayList;
import java.util.Objects;

public class StudentService {

    public static class GradeInfo {
        public GradeInfo(Task task, Grade grade) {
            this.task = task;
            this.grade = grade;
        }

        public Task task;
        public Grade grade;
    }

    public static class AttendanceInfo {
        public AttendanceInfo(Lesson lesson, Mark mark) {
            this.lesson = lesson;
            this.mark = mark;
        }

        public Lesson lesson;
        public Mark mark;
    }


    public StudentService(Storages storages, Student student) {
        this.storages = storages;
        this.student = student;
    }

    public final ArrayList<Subject> getMySubjects() {
        var subjects = storages.getSubjects().stream().filter(student::hasSubject);
        return new ArrayList<>(subjects.toList());
    }

    public final ArrayList<Group> getMyGroups() {
        var groups = storages.getGroups().stream().filter(i -> i.hasStudent(student));
        return new ArrayList<>(groups.toList());
    }

    public final ArrayList<GradeInfo> seeGrades() {
        var result = new ArrayList<GradeInfo>();
        getMySubjects().forEach(i -> fillGradeTableWithSubject(result, i));
        return result;
    }

    public final ArrayList<GradeInfo> seeGrades(Subject subject) {
        if (!student.hasSubject(subject)) {
            throw new IllegalArgumentException("logged in student does not have this subject");
        }
        var result = new ArrayList<GradeInfo>();
        subject.getTasks().forEach(t -> result.add(makeGradeInfoFromTask(t)));
        return result;
    }

    public final ArrayList<GradeInfo> seeGrades(Task.Type type) {
        var result = new ArrayList<GradeInfo>();

        getMySubjects().forEach(s -> {
            s.getTasks()
                    .stream()
                    .filter(t -> Objects.equals(t.getType(), type))
                    .forEach(i -> result.add(makeGradeInfoFromTask(i)));
        });

        return result;
    }


    public final ArrayList<Task> seeAcademicDebt() {
        var result = new ArrayList<Task>();

        getMySubjects().forEach(s -> {
            s.getTasks().stream().filter(t -> !t.hasSuccessfullyDone(student)).forEach(result::add);
        });

        return result;
    }

    public final ArrayList<AttendanceInfo> seeAttendance() {
        var result = new ArrayList<AttendanceInfo>();
        var subjects = getMySubjects();
        subjects.forEach(i -> fillAttendanceTableWithSubject(result, i));
        return result;
    }

    public final ArrayList<AttendanceInfo> seeAttendance(Subject subject) {
        if (!student.hasSubject(subject)) {
            throw new IllegalArgumentException("the logged in student does not have this subject");
        }
        var result = new ArrayList<AttendanceInfo>();
        fillAttendanceTableWithSubject(result, subject);
        return result;
    }

    public final ArrayList<AttendanceInfo> seeAttendance(Lesson.Type type) {
        var result = new ArrayList<AttendanceInfo>();
        getMySubjects().forEach(s -> {
            var lessons = s.getLessons().stream().filter(l -> Objects.equals(l.getType(), type));
            lessons.forEach(l -> {
                result.add(makeAttendanceInfoFromLesson(l));
            });
        });
        return result;
    }

    public final void submitTask(Task task, Attempt attempt) {
        task.submit(student, attempt);
    }

    private final void fillAttendanceTableWithSubject(ArrayList<AttendanceInfo> table, Subject subject) {
        subject.getLessons().forEach(lesson -> {
            table.add(makeAttendanceInfoFromLesson(lesson));
        });
    }

    private final void fillGradeTableWithSubject(ArrayList<GradeInfo> table, Subject subject) {
        subject.getTasks().forEach(task -> {
            table.add(makeGradeInfoFromTask(task));
        });
    }


    private final AttendanceInfo makeAttendanceInfoFromLesson(Lesson lesson) {
        if (lesson.hasMark(student)) {
            return new AttendanceInfo(lesson, lesson.getStudentMark(student));
        }
        return new AttendanceInfo(lesson, new Mark(student, Mark.Value.NOT_PRESENTED));
    }

    private final GradeInfo makeGradeInfoFromTask(Task task) {
        try {
            var submission = task.getSubmissionForStudent(student);
            var grade = submission.getMaxGrade();
            return new GradeInfo(task, grade);
        } catch (RuntimeException ignored) {
            return new GradeInfo(task, new Grade(0));
        }
    }


    private final Storages storages;
    private final Student student;
}

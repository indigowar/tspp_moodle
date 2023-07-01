package org.tspp.services;

import java.util.Objects;

public class AuthService {

    public AuthService(Storages storages) {
        this.storages = storages;
    }

    public StudentService logInAsStudent(String email, String password) throws RuntimeException {
        var student = storages.getStudents()
                .stream()
                .filter(i -> Objects.equals(email, i.getMail()) && i.validatePassword(password))
                .findFirst();
        if (student.isEmpty()) {
            throw new RuntimeException("given credentials are invalid");
        }
        return new StudentService(storages, student.get());
    }

    public TeacherService logInAsTeacher(String email, String password) throws RuntimeException {
        var teacher = storages.getTeachers()
                .stream()
                .filter(i -> Objects.equals(i.getMail(), email) && i.validatePassword(password))
                .findFirst();
        if (teacher.isEmpty()) {
            throw new RuntimeException("given credentials are invalid");
        }
        return new TeacherService(storages, teacher.get());
    }

    private final Storages storages;
}

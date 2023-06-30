package org.tspp.ed_process.tasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import org.tspp.ed_process.Group;
import org.tspp.ed_process.Subject;
import org.tspp.users.Student;
import org.tspp.users.Teacher;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    Student student1;
    Student student2;
    Group group;
    Teacher teacher;


    Subject subject;
    Requirement requirement;

    @BeforeEach
    public void setUp() {
        student1 = new Student("John", "John", "Doe", "jjdoe@gmail.com", "password");
        student2 = new Student("Jane", "Jenny", "Smith", "jjsmith@gmail.com", "password");
        teacher = new Teacher("Alex", "Andrew", "Roberts", "roberts_work@gmail.com", "password");

        group = new Group("test group");
        group.addStudent(this.student1);

        requirement = new FileRequirementByExtension(new ArrayList<>(Arrays.asList(".txt")));


        subject = new Subject("Test Subject", teacher);
        subject.addGroup(group);
    }

    @Test
    public void testSubmit_ValidAttempt_AddsSubmission() {
        Task task = new Task(Task.Type.TEST, "Test Task", subject, requirement, 5);
        Attempt attempt = new Attempt(new ArrayList<>(Arrays.asList("file1.txt")));

        task.submit(student1, attempt);

        Submission submission = task.getSubmissionForStudent(student1);
        assertTrue(submission.hasUncheckedAttempts());
    }

    @Test
    public void testSubmit_InvalidAttempt_ThrowsIllegalArgumentException() {
        Task task = new Task(Task.Type.TEST, "Test Task", subject, requirement, 5);
        Attempt invalidAttempt = new Attempt(new ArrayList<>(Arrays.asList("file.jpg")));

        // Throws because the requirements expect only files whose ends with .txt
        assertThrows(IllegalArgumentException.class, () -> {
            task.submit(student1, invalidAttempt);
        });
    }

    @Test
    public void testSubmit_StudentHasNoSubject_ThrowsIllegalArgumentException() {
        Task task = new Task(Task.Type.TEST, "Test Task", subject, requirement, 5);
        Attempt attempt = new Attempt(new ArrayList<>(Arrays.asList("file1.txt")));

        // Throws because we didn't add student2 to group that has this subject.
        assertThrows(IllegalArgumentException.class, () -> {
            task.submit(student2, attempt);
        });
    }

    @Test
    public void testGetSubmissionForStudent_ExistingStudent_ReturnsSubmission() {
        Task task = new Task(Task.Type.TEST, "Test Task", subject, requirement, 5);
        Attempt attempt = new Attempt(new ArrayList<>(Arrays.asList("file1.txt")));

        task.submit(student1, attempt);

        Submission submission = task.getSubmissionForStudent(student1);

        assertNotNull(submission);
    }

    @Test
    public void testGetSubmissionForStudent_NonExistingStudent_ThrowsRuntimeException() {
        Task task = new Task(Task.Type.TEST, "Test Task", subject, requirement, 5);

        // Throws here because student didn't submit anything to this task.
        assertThrows(RuntimeException.class, () -> {
            task.getSubmissionForStudent(student1);
        });
    }

    @Test
    public void testGetPendingSubmission_HasPendingSubmission_ReturnsSubmission() {
        Task task = new Task(Task.Type.TEST, "Test Task", subject, requirement, 5);

        Attempt attempt = new Attempt(new ArrayList<>(Arrays.asList("file1.txt")));
        task.submit(student1, attempt);

        Submission pendingSubmission = task.getPendingSubmission();

        assertNotNull(pendingSubmission);
        assertTrue(pendingSubmission.hasUncheckedAttempts());
    }

    @Test
    public void testGetPendingSubmission_NoPendingSubmission_ThrowsRuntimeException() {
        Task task = new Task(Task.Type.TEST, "Test Task", subject, requirement, 5);

        assertThrows(RuntimeException.class, () -> {
            task.getPendingSubmission();
        });
    }
}
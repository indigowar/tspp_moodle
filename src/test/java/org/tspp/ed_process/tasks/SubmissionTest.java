package org.tspp.ed_process.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubmissionTest {
    private final Grade grade_20 = new Grade(20);
    private final Grade grade_30 = new Grade(30);
    private final Grade grade_40 = new Grade(40);
    private final Grade grade_60 = new Grade(60);

    @Test
    public void testLimitConstructor() {
        Submission s = new Submission(5);
        assertEquals(5, s.getLimit());

        s = new Submission(1);
        assertEquals(1, s.getLimit());

        // Cannot create a Submission with limit below or equal zero.
        assertThrows(IllegalArgumentException.class, () -> new Submission(0));
        assertThrows(IllegalArgumentException.class, () -> new Submission(-1));
    }

    @Test
    public void testAddAttempt() {
        Submission s = new Submission(3);
        Attempt a1 = new Attempt("some text");
        Attempt a2 = new Attempt("some text");
        Attempt a3 = new Attempt("some text");
        Attempt a4 = new Attempt("some text");

        s.addAttempt(a1);
        s.addAttempt(a2);
        s.addAttempt(a3);

        // here buffer of attempts is already full, we can't add new.
        assertThrows(RuntimeException.class, () -> s.addAttempt(a4));

        // trying to add an attempt that is already graded.
        Attempt a5 = new Attempt("some text");
        a5.grade(new Grade(50));
        assertThrows(IllegalArgumentException.class, () -> s.addAttempt(a5));
    }

    @Test
    public void testHasUncheckedAttempts() {
        Submission s = new Submission(5);
        Attempt a1 = new Attempt("some text");
        Attempt a2 = new Attempt("some text");
        Attempt a3 = new Attempt("some text");

        s.addAttempt(a1);
        s.addAttempt(a2);
        s.addAttempt(a3);

        assertTrue(s.hasUncheckedAttempts());

        a1.grade(grade_60);
        a2.grade(grade_60);
        a3.grade(grade_60);

        assertFalse(s.hasUncheckedAttempts());

        Attempt a4 = new Attempt("some text");
        s.addAttempt(a4);
        assertTrue(s.hasUncheckedAttempts());
    }

    @Test
    public void testGetUnchecked() {
        Submission s = new Submission(3);
        Attempt a1 = new Attempt("some text");
        Attempt a2 = new Attempt("some text");
        Attempt a3 = new Attempt("some text");

        s.addAttempt(a1);
        s.addAttempt(a2);
        s.addAttempt(a3);

        assertEquals(a1, s.getUnchecked());

        a1.grade(grade_60);
        assertEquals(a2, s.getUnchecked());
    }

    @Test
    public void testGetMaxGrade() {
        Submission s = new Submission(5);
        Attempt a1 = new Attempt("some text");
        Attempt a2 = new Attempt("some text");
        Attempt a3 = new Attempt("some text");

        Grade g1 = new Grade(20);
        Grade g2 = new Grade(30);
        Grade g3 = new Grade(40);

        s.addAttempt(a1);
        s.addAttempt(a2);
        s.addAttempt(a3);

        // Without grading any attempts, getMaxGrade should return 0 grade
        assertEquals(new Grade(0), s.getMaxGrade());

        a1.grade(grade_20);
        a2.grade(grade_30);
        a3.grade(grade_40);

        // max grade will g3, because it has the highest value - 40.
        assertEquals(g3, s.getMaxGrade());

        Attempt a4 = new Attempt("some text");
        s.addAttempt(a4);
        assertEquals(g3, s.getMaxGrade());
    }

    @Test
    public void testIsSuccessfullyDone() {
        Submission s = new Submission(4);
        Attempt a1 = new Attempt("some text");
        Attempt a2 = new Attempt("some text");
        Attempt a3 = new Attempt("some text");

        // Without attempts the submission is not successfully done.
        assertFalse(s.isSuccessfullyDone());


        s.addAttempt(a1);
        s.addAttempt(a2);
        s.addAttempt(a3);

        // Since all attempts are not checked, the submission is still unsuccessfully done.
        assertFalse(s.isSuccessfullyDone());

        // Here all attempts are marked below 60, so the submission is still unsuccessfully done.
        a1.grade(grade_20);
        a2.grade(grade_30);
        a3.grade(grade_40);


        // Here we add a new attempt, after that we mark it as 60(pass-mark). So the submission should be successfully done.
        Attempt a4 = new Attempt("some text");
        s.addAttempt(a4);
        s.getUnchecked().grade(grade_60);

        assertTrue(s.isSuccessfullyDone());
    }

}

package org.tspp.ed_process.tasks;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AttemptTest {

    @Test
    public void testIsGraded() {
        Attempt attempt = new Attempt("some text");
        assertFalse(attempt.isGraded());

        attempt.grade(new Grade(90));
        assertTrue(attempt.isGraded());
    }

    @Test
    public void testGrade() {
        Attempt attempt = new Attempt("some text");

        assertThrows(RuntimeException.class, () -> {
            attempt.grade(new Grade(80));
            attempt.grade(new Grade(90));
        });

        assertEquals(80, attempt.getGrade().get());
    }

    @Test
    public void testHasFiles() {
        Attempt attempt1 = new Attempt("some text");
        assertFalse(attempt1.hasFiles());

        ArrayList<String> files = new ArrayList<>();
        files.add("file1.txt");
        files.add("file2.txt");
        Attempt attempt2 = new Attempt(files);
        assertTrue(attempt2.hasFiles());
    }

    @Test
    public void testHasText() {
        Attempt attempt1 = new Attempt(new ArrayList<String>());
        assertFalse(attempt1.hasText());

        Attempt attempt2 = new Attempt("some text");
        assertTrue(attempt2.hasText());
    }

    @Test
    public void testGetFiles() {
        ArrayList<String> files = new ArrayList<>();
        files.add("file1.txt");
        files.add("file2.txt");
        Attempt attempt = new Attempt(files);
        assertEquals(files, attempt.getFiles());
    }

    @Test
    public void testGetText() {
        Attempt attempt = new Attempt("some text");
        assertEquals("some text", attempt.getText());
    }

}

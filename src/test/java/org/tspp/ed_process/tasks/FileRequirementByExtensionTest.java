package org.tspp.ed_process.tasks;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileRequirementByExtensionTest {

    @Test
    public void testIsSatisfiedBy_NoFiles_ReturnsTrue() {
        FileRequirementByExtension fileReq = new FileRequirementByExtension(new ArrayList<String>());

        Attempt attempt = new Attempt("some text", new ArrayList<>(List.of("file.txt", "document.docx")));

        boolean isSatisfied = fileReq.isSatisfiedBy(attempt);
        assertTrue(isSatisfied);
    }

    @Test
    public void testIsSatisfiedBy_FilesWithCorrectExtensions_ReturnsTrue() {
        ArrayList<String> extensions = new ArrayList<>(Arrays.asList(".jpg", ".png"));
        FileRequirementByExtension fileReq = new FileRequirementByExtension(extensions);

        Attempt attempt = new Attempt(new ArrayList<>(List.of("file1.jpg", "file2.jpg", "file3.png")));

        boolean isSatisfied = fileReq.isSatisfiedBy(attempt);
        assertTrue(isSatisfied);
    }

    @Test
    public void testIsSatisfiedBy_FilesWithIncorrectExtensions_ReturnsFalse() {
        ArrayList<String> extensions = new ArrayList<>(List.of(".jpg", ".png"));
        FileRequirementByExtension fileReq = new FileRequirementByExtension(extensions);
        Attempt attempt = new Attempt(new ArrayList<>(List.of("file1.txt", "file2.gif")));

        boolean isSatisfied = fileReq.isSatisfiedBy(attempt);

        assertFalse(isSatisfied);
    }

    @Test
    public void testIsSatisfiedBy_FilesWithMixOfExtensions_ReturnsFalse() {
        ArrayList<String> extensions = new ArrayList<>(Arrays.asList(".jpg", ".png"));
        FileRequirementByExtension fileReq = new FileRequirementByExtension(extensions);

        Attempt attempt = new Attempt(new ArrayList<>(Arrays.asList("file1.jpg", "file2.txt", "file3.png")));

        boolean isSatisfied = fileReq.isSatisfiedBy(attempt);
        assertFalse(isSatisfied);
    }
}
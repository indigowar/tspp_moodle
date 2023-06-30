package org.tspp.ed_process.tasks;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TextRequirementTest {

    @Test
    public void testIsSatisfiedByWithHasTextTrue() {
        Attempt attemptWithText = new Attempt("Some text");
        TextRequirement textReq = new TextRequirement(true);
        assertTrue(textReq.isSatisfiedBy(attemptWithText));
    }

    @Test
    public void testIsSatisfiedByWithHasTextFalse() {
        Attempt attemptWithoutText = new Attempt(new ArrayList<>());
        TextRequirement textReq = new TextRequirement(false);
        assertTrue(textReq.isSatisfiedBy(attemptWithoutText));
    }

    @Test
    public void testIsSatisfiedByWithHasTextTrueAndNoText() {
        Attempt attemptWithoutText = new Attempt(new ArrayList<>());
        TextRequirement textReq = new TextRequirement(true);
        assertFalse(textReq.isSatisfiedBy(attemptWithoutText));
    }

    @Test
    public void testIsSatisfiedByWithHasTextFalseAndSomeText() {
        Attempt attemptWithText = new Attempt("Some text");
        TextRequirement textReq = new TextRequirement(false);
        assertFalse(textReq.isSatisfiedBy(attemptWithText));
    }
}
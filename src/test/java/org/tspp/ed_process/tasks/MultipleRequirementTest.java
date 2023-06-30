package org.tspp.ed_process.tasks;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleRequirementTest {

    @Test
    public void testIsSatisfiedByWhenAllRequirementsPassed() {
        ArrayList<Requirement> requirements = new ArrayList<>();
        requirements.add(new TextRequirement(true));
        requirements.add(new FileRequirementByExtension(new ArrayList<>()));
        MultipleRequirement multiReq = new MultipleRequirement(requirements);
        Attempt attempt = new Attempt("hello world");
        assertTrue(multiReq.isSatisfiedBy(attempt));
    }

    @Test
    public void testIsSatisfiedByWhenOneRequirementFailed() {
        ArrayList<Requirement> requirements = new ArrayList<>();
        requirements.add(new TextRequirement(true));
        requirements.add(new FileRequirementByExtension(new ArrayList<>(Arrays.asList(".docx"))));
        MultipleRequirement multiReq = new MultipleRequirement(requirements);
        Attempt attempt = new Attempt("hi");


        assertFalse(multiReq.isSatisfiedBy(attempt));
    }

    @Test
    public void testIsSatisfiedByWithEmptyRequirements() {
        ArrayList<Requirement> emptyRequirements = new ArrayList<>();
        MultipleRequirement multiReq = new MultipleRequirement(emptyRequirements);
        Attempt attempt = new Attempt("testing");
        assertTrue(multiReq.isSatisfiedBy(attempt));
    }
}
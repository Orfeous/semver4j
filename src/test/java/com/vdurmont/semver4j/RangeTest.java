package com.vdurmont.semver4j;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class RangeTest {
    @Test public void isSatisfiedBy_EQ() {
        Range range = new Range("1.2.3", Range.RangeOperator.EQ);

        // SAME VERSION
        assertTrue(range.isSatisfiedBy("1.2.3"));

        // GREATER
        assertFalse(range.isSatisfiedBy("2.2.3")); // major
        assertFalse(range.isSatisfiedBy("1.3.3")); // minor
        assertFalse(range.isSatisfiedBy("1.2.4")); // patch

        // LOWER
        assertFalse(range.isSatisfiedBy("0.2.3")); // major
        assertFalse(range.isSatisfiedBy("1.1.3")); // minor
        assertFalse(range.isSatisfiedBy("1.2.2")); // patch
        Range rangeWithSuffix = new Range("1.2.3-alpha", Range.RangeOperator.EQ);
        assertFalse(rangeWithSuffix.isSatisfiedBy("1.2.3")); // null suffix
        assertFalse(rangeWithSuffix.isSatisfiedBy("1.2.3-beta")); // non null suffix
    }

    @Test public void isSatisfiedBy_LT() {
        Range range = new Range("1.2.3", Range.RangeOperator.LT);

        assertFalse(range.isSatisfiedBy("1.2.3"));
        assertFalse(range.isSatisfiedBy("1.2.4"));
        assertTrue(range.isSatisfiedBy("1.2.2"));
    }

    @Test public void isSatisfiedBy_LTE() {
        Range range = new Range("1.2.3", Range.RangeOperator.LTE);

        assertTrue(range.isSatisfiedBy("1.2.3"));
        assertFalse(range.isSatisfiedBy("1.2.4"));
        assertTrue(range.isSatisfiedBy("1.2.2"));
    }

    @Test public void isSatisfiedBy_GT() {
        Range range = new Range("1.2.3", Range.RangeOperator.GT);

        assertFalse(range.isSatisfiedBy("1.2.3"));
        assertFalse(range.isSatisfiedBy("1.2.2"));
        assertTrue(range.isSatisfiedBy("1.2.4"));
    }

    @Test public void isSatisfiedBy_GTE() {
        Range range = new Range("1.2.3", Range.RangeOperator.GTE);

        assertTrue(range.isSatisfiedBy("1.2.3"));
        assertFalse(range.isSatisfiedBy("1.2.2"));
        assertTrue(range.isSatisfiedBy("1.2.4"));
    }
}

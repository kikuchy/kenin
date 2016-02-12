package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class AlphaNumericConditionTest {
    @Test
    public void testValidate() throws Exception {
        Condition<CharSequence, String> condition = new AlphaNumericCondition<>("not alphanumeric");
        ValidationResult<String> one = condition.validate("1mHungry");
        assertTrue(one.isValid());
        ValidationResult<String> two = condition.validate("WhyTheLifeIsHardToLive");
        assertTrue(two.isValid());
        ValidationResult<String> three = condition.validate("0923795");
        assertTrue(three.isValid());
        ValidationResult<String> four = condition.validate("Sleeping? It's heaven!!");
        assertFalse(four.isValid());
        ValidationResult<String> five = condition.validate("");
        assertTrue(five.isValid());
    }
}
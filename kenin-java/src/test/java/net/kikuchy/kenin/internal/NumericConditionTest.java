package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class NumericConditionTest {
    @Test
    public void testValidate() throws Exception {
        Condition<CharSequence, String> condition = new NumericCondition<>("not a number");
        ValidationResult<String> one = condition.validate("123456");
        assertTrue(one.isValid());
        ValidationResult<String> two = condition.validate("+123456");
        assertFalse(two.isValid());
        // only number character is allowed
        ValidationResult<String> three = condition.validate("123456.000");
        assertFalse(three.isValid());
    }
}
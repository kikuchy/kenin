package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class SameConditionTest {

    @Test
    public void testValidate() throws Exception {
        Condition<CharSequence, String> condition = new SameCondition<>("foobar", "be same");
        ValidationResult<String> one = condition.validate("hogefuga");
        assertFalse(one.isValid());
        ValidationResult<String> two = condition.validate("foobar");
        assertTrue(two.isValid());
    }
}
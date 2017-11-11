package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FullPatternMatchConditionTest {
    @Test
    public void testValidate() throws Exception {
        Condition<CharSequence, String> condition =
                new FullPatternMatchCondition<>("^abc\\def", "not matched");
        ValidationResult<String> one = condition.validate("abc4ef");
        assertTrue(one.isValid());
        ValidationResult<String> two = condition.validate("abcdef");
        assertFalse(two.isValid());
        ValidationResult<String> three = condition.validate("abc4efg");
        assertFalse(three.isValid());
    }
}

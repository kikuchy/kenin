package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class PartialPatternMatchConditionTest {

    @Test
    public void testValidate() throws Exception {
        Condition<CharSequence, String> condition =
                new PartialPatternMatchCondition<>("^abc\\def", "not matched");
        ValidationResult<String> one = condition.validate("abc4efghijklmnop");
        assertTrue(one.isValid());
        ValidationResult<String> two = condition.validate("abcdefghijklmnop");
        assertFalse(two.isValid());
    }
}
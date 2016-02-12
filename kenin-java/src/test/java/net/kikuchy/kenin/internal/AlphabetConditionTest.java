package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class AlphabetConditionTest {
    @Test
    public void testValidate() throws Exception {
        Condition<CharSequence, String> condition = new AlphabetCondition<>("not alphabet");
        ValidationResult<String> one = condition.validate("HisNameIsAlex");
        assertTrue(one.isValid());
        ValidationResult<String> two = condition.validate("Her name is Ann");
        assertFalse(two.isValid());
        ValidationResult<String> three = condition.validate("WhyJava's0peratorsAre-NotFunction?");
        assertFalse(three.isValid());
    }
}
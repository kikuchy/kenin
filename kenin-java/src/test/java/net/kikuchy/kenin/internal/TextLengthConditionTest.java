package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class TextLengthConditionTest {

    @Test
    public void testValidateEqual() throws Exception {
        Condition<CharSequence, String> condition =
                new TextLengthCondition<>(4, TextLengthCondition.Comparison.EQUAL, "not valid");
        ValidationResult<String> one = condition.validate("abcd");
        assertTrue(one.isValid());
        ValidationResult<String> two = condition.validate("abc");
        assertFalse(two.isValid());
        ValidationResult<String> three = condition.validate("abc");
        assertFalse(three.isValid());
    }

    @Test
    public void testValidateLess() throws Exception {
        Condition<CharSequence, String> condition =
                new TextLengthCondition<>(4, TextLengthCondition.Comparison.LESS_THAN, "not valid");
        ValidationResult<String> one = condition.validate("abc");
        assertTrue(one.isValid());
        ValidationResult<String> two = condition.validate("abcd");
        assertFalse(two.isValid());
        ValidationResult<String> three = condition.validate("abcde");
        assertFalse(three.isValid());
    }

    @Test
    public void testValidateLe() throws Exception {
        Condition<CharSequence, String> condition =
                new TextLengthCondition<>(4, TextLengthCondition.Comparison.LESS_THAN_OR_EQUAL, "not valid");
        ValidationResult<String> one = condition.validate("abc");
        assertTrue(one.isValid());
        ValidationResult<String> two = condition.validate("abcd");
        assertTrue(two.isValid());
        ValidationResult<String> three = condition.validate("abcde");
        assertFalse(three.isValid());
    }

    @Test
    public void testValidateGreater() throws Exception {
        Condition<CharSequence, String> condition =
                new TextLengthCondition<>(4, TextLengthCondition.Comparison.GREATER_THAN, "not valid");
        ValidationResult<String> one = condition.validate("abc");
        assertFalse(one.isValid());
        ValidationResult<String> two = condition.validate("abcd");
        assertFalse(two.isValid());
        ValidationResult<String> three = condition.validate("abcde");
        assertTrue(three.isValid());
    }

    @Test
    public void testValidateGe() throws Exception {
        Condition<CharSequence, String> condition =
                new TextLengthCondition<>(4, TextLengthCondition.Comparison.GREATER_THAN_OR_EQUAL, "not valid");
        ValidationResult<String> one = condition.validate("abc");
        assertFalse(one.isValid());
        ValidationResult<String> two = condition.validate("abcd");
        assertTrue(two.isValid());
        ValidationResult<String> three = condition.validate("abcde");
        assertTrue(three.isValid());
    }
}
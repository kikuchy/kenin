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

    @Test
    public void testValidateLazy() throws Exception {
        TestGetter<CharSequence> lazy = new TestGetter<>();
        Condition<CharSequence, String> condition = new SameCondition<>(lazy, "be same");
        lazy.setValue("foobar");
        ValidationResult<String> one = condition.validate("hogefuga");
        assertFalse(one.isValid());
        ValidationResult<String> two = condition.validate("foobar");
        assertTrue(two.isValid());
        lazy.setValue("Keep Calm and I'm sleeping");
        ValidationResult<String> three = condition.validate("Keep Calm and I'm sleeping");
        assertTrue(three.isValid());
    }

    static class TestGetter<T> implements SameCondition.LazyGetter<T> {
        private T value;
        public void setValue(T v) {
            value = v;
        }

        @Override
        public T get() {
            return value;
        }
    }
}
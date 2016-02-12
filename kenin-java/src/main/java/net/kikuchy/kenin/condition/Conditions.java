package net.kikuchy.kenin.condition;

import net.kikuchy.kenin.internal.PatternMatchCondition;
import net.kikuchy.kenin.internal.RequireCondition;
import net.kikuchy.kenin.internal.SameCondition;

/**
 * Utilities for using conditions.
 */
public final class Conditions {
    public static Condition<CharSequence, String> requireField() {
        return new RequireCondition<>("This field is required.");
    }

    public static Condition<CharSequence, String> requireField(String errorMessage) {
        return new RequireCondition<>(errorMessage);
    }

    public static Condition<CharSequence, String> same(CharSequence expected) {
        return new SameCondition<>(expected, String.format("Value must be same with \"%s\"", expected));
    }

    public static Condition<CharSequence, String> same(CharSequence expected, String errorMessage) {
        return new SameCondition<>(expected, errorMessage);
    }

    public static Condition<CharSequence, String> pattern(String pattern) {
        return new PatternMatchCondition<>(pattern,
                String.format("Value must be match with the pattern \"%s\"", pattern));
    }

    public static Condition<CharSequence, String> pattern(String pattern, String errorMessage) {
        return new PatternMatchCondition<>(pattern, errorMessage);
    }
}

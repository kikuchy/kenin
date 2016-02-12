package net.kikuchy.kenin.condition;

import net.kikuchy.kenin.internal.AlphaNumericCondition;
import net.kikuchy.kenin.internal.AlphabetCondition;
import net.kikuchy.kenin.internal.NumericCondition;
import net.kikuchy.kenin.internal.PatternMatchCondition;
import net.kikuchy.kenin.internal.RequireCondition;
import net.kikuchy.kenin.internal.SameCondition;
import net.kikuchy.kenin.internal.SameTextCondition;
import net.kikuchy.kenin.internal.TextLengthCondition;

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
        return new SameTextCondition<>(expected, String.format("Value must be same with \"%s\"", expected));
    }

    public static Condition<CharSequence, String> same(CharSequence expected, String errorMessage) {
        return new SameTextCondition<>(expected, errorMessage);
    }

    public static Condition<Boolean, String> same(boolean expected, String errorMessage) {
        return new SameCondition<Boolean, String>(expected, errorMessage) {
            @Override
            protected boolean equalsBetween(Boolean v1, Boolean v2) {
                return v1.booleanValue() == v2.booleanValue();
            }
        };
    }

    public static Condition<Integer, String> same(int expected, String errorMessage) {
        return new SameCondition<Integer, String>(expected, errorMessage) {
            @Override
            protected boolean equalsBetween(Integer v1, Integer v2) {
                return v1.intValue() == v2.intValue();
            }
        };
    }

    public static Condition<CharSequence, String> pattern(String pattern) {
        return new PatternMatchCondition<>(pattern,
                String.format("Value must be match with the pattern \"%s\"", pattern));
    }

    public static Condition<CharSequence, String> pattern(String pattern, String errorMessage) {
        return new PatternMatchCondition<>(pattern, errorMessage);
    }

    public static Condition<CharSequence, String> lengthJust(int length) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.EQUAL,
                String.format("Value must be just %d characters.", length));
    }

    public static Condition<CharSequence, String> lengthJust(int length, String errorMessage) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.EQUAL, errorMessage);
    }

    public static Condition<CharSequence, String> lengthMin(int length) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.GREATER_THAN_OR_EQUAL,
                String.format("Value must be longer than %d characters.", length));
    }

    public static Condition<CharSequence, String> lengthMin(int length, String errorMessage) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.GREATER_THAN_OR_EQUAL, errorMessage);
    }

    public static Condition<CharSequence, String> lengthMax(int length) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.LESS_THAN_OR_EQUAL,
                String.format("Value must be shorter than %d characters.", length));
    }

    public static Condition<CharSequence, String> lengthMax(int length, String errorMessage) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.LESS_THAN_OR_EQUAL, errorMessage);
    }

    public static Condition<CharSequence, String> numeric() {
        return new NumericCondition<>("Value must be a number.");
    }

    public static Condition<CharSequence, String> numeric(String errorMessage) {
        return new NumericCondition<>(errorMessage);
    }

    public static Condition<CharSequence, String> alphabet() {
        return new AlphabetCondition<>("Value must be alphabets.");
    }

    public static Condition<CharSequence, String> alphabet(String errorMessage) {
        return new AlphabetCondition<>(errorMessage);
    }

    public static Condition<CharSequence, String> alphanumeric() {
        return new AlphaNumericCondition<>("Value must be alphanumeric.");
    }

    public static Condition<CharSequence, String> alphanumeric(String errorMessage) {
        return new AlphaNumericCondition<>(errorMessage);
    }
}

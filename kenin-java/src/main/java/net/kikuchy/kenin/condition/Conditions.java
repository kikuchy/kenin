package net.kikuchy.kenin.condition;

import net.kikuchy.kenin.internal.AlphaNumericCondition;
import net.kikuchy.kenin.internal.AlphabetCondition;
import net.kikuchy.kenin.internal.AssertTrueCondition;
import net.kikuchy.kenin.internal.FullPatternMatchCondition;
import net.kikuchy.kenin.internal.NumericCondition;
import net.kikuchy.kenin.internal.PartialPatternMatchCondition;
import net.kikuchy.kenin.internal.RequireCondition;
import net.kikuchy.kenin.internal.SameCondition;
import net.kikuchy.kenin.internal.SameTextCondition;
import net.kikuchy.kenin.internal.TextLengthCondition;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Utilities for using conditions.
 */
public final class Conditions {
    @NotNull
    public static Condition<CharSequence, String> requireField() {
        return new RequireCondition<>("This field is required.");
    }

    @NotNull
    public static Condition<CharSequence, String> requireField(String errorMessage) {
        return new RequireCondition<>(errorMessage);
    }

    @NotNull
    public static Condition<Boolean, String> requireChecked(String errorMessage) {
        return new AssertTrueCondition<>(errorMessage);
    }

    @NotNull
    public static Condition<Boolean, String> requireChecked() {
        return requireChecked("This field is required.");
    }

    @NotNull
    public static Condition<CharSequence, String> sameText(CharSequence expected) {
        return new SameTextCondition<>(expected, String.format("Value must be same with \"%s\"", expected));
    }

    @NotNull
    public static Condition<CharSequence, String> sameText(CharSequence expected, String errorMessage) {
        return new SameTextCondition<>(expected, errorMessage);
    }

    @NotNull
    public static <V extends CharSequence> Condition<V, String> sameText(
            SameCondition.LazyGetter<V> expected, String errorMessage) {
        return new SameCondition<V, String>(expected, errorMessage) {
            @Override
            protected boolean equalsBetween(V v1, V v2) {
                return v1.toString().equals(v2.toString());
            }
        };
    }

    @NotNull
    public static Condition<Boolean, String> sameBool(
            SameCondition.LazyGetter<Boolean> expected, String errorMessage) {
        return new SameCondition<Boolean, String>(expected, errorMessage) {
            @Override
            protected boolean equalsBetween(Boolean v1, Boolean v2) {
                return v1.booleanValue() == v2.booleanValue();
            }
        };
    }

    @NotNull
    public static Condition<Integer, String> sameInt(
            SameCondition.LazyGetter<Integer> expected, String errorMessage) {
        return new SameCondition<Integer, String>(expected, errorMessage) {
            @Override
            protected boolean equalsBetween(Integer v1, Integer v2) {
                return v1.intValue() == v2.intValue();
            }
        };
    }

    @NotNull
    public static Condition<CharSequence, String> partialPattern(String pattern) {
        return new PartialPatternMatchCondition<>(pattern,
                String.format("Value must be match with the Pattern \"%s\"", pattern));
    }

    @NotNull
    public static Condition<CharSequence, String> partialPattern(String pattern, String errorMessage) {
        return new PartialPatternMatchCondition<>(pattern, errorMessage);
    }

    @NotNull
    public static Condition<CharSequence, String> fullPattern(String pattern) {
        return new FullPatternMatchCondition<>(pattern,
                String.format("Value must be match perfectly with the Pattern \"%s\"", pattern));
    }

    @NotNull
    public static Condition<CharSequence, String> fullPattern(String pattern, String errorMessage){
        return new FullPatternMatchCondition<>(pattern, errorMessage);
    }

    @NotNull
    public static Condition<CharSequence, String> lengthJust(int length) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.EQUAL,
                String.format(Locale.getDefault(), "Value must be just %d characters.", length));
    }

    @NotNull
    public static Condition<CharSequence, String> lengthJust(int length, String errorMessage) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.EQUAL, errorMessage);
    }

    @NotNull
    public static Condition<CharSequence, String> lengthMin(int length) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.GREATER_THAN_OR_EQUAL,
                String.format(Locale.getDefault(), "Value must be longer than %d characters.", length));
    }

    @NotNull
    public static Condition<CharSequence, String> lengthMin(int length, String errorMessage) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.GREATER_THAN_OR_EQUAL, errorMessage);
    }

    @NotNull
    public static Condition<CharSequence, String> lengthMax(int length) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.LESS_THAN_OR_EQUAL,
                String.format(Locale.getDefault(), "Value must be shorter than %d characters.", length));
    }

    @NotNull
    public static Condition<CharSequence, String> lengthMax(int length, String errorMessage) {
        return new TextLengthCondition<>(
                length, TextLengthCondition.Comparison.LESS_THAN_OR_EQUAL, errorMessage);
    }

    @NotNull
    public static Condition<CharSequence, String> numeric() {
        return new NumericCondition<>("Value must be a number.");
    }

    @NotNull
    public static Condition<CharSequence, String> numeric(String errorMessage) {
        return new NumericCondition<>(errorMessage);
    }

    @NotNull
    public static Condition<CharSequence, String> alphabet() {
        return new AlphabetCondition<>("Value must be alphabets.");
    }

    @NotNull
    public static Condition<CharSequence, String> alphabet(String errorMessage) {
        return new AlphabetCondition<>(errorMessage);
    }

    @NotNull
    public static Condition<CharSequence, String> alphanumeric() {
        return new AlphaNumericCondition<>("Value must be alphanumeric.");
    }

    @NotNull
    public static Condition<CharSequence, String> alphanumeric(String errorMessage) {
        return new AlphaNumericCondition<>(errorMessage);
    }
}

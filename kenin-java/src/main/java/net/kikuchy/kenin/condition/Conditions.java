package net.kikuchy.kenin.condition;

import net.kikuchy.kenin.internal.RequireCondition;
import net.kikuchy.kenin.internal.SameCondition;

/**
 * Utilities for using conditions.
 */
public final class Conditions {
    public static Condition<CharSequence> requireField() {
        return new RequireCondition();
    }

    public static Condition<CharSequence> requireField(String errorMessage) {
        return new RequireCondition(errorMessage);
    }

    public static Condition<CharSequence> same(CharSequence expected) {
        return new SameCondition(expected);
    }

    public static Condition<CharSequence> same(CharSequence expected, String errorMessage) {
        return new SameCondition(expected, errorMessage
        );
    }
}

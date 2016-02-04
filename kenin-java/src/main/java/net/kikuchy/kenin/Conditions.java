package net.kikuchy.kenin;

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
}
package net.kikuchy.kenin;

import net.kikuchy.kenin.internal.RequireCondition;
import net.kikuchy.kenin.internal.SameCondition;

/**
 * Created by kikuchy on 16/02/04.
 */
public final class Conditions {
    public static Condition<CharSequence> requireField() {
        return new RequireCondition();
    }

    public static Condition<CharSequence> same(CharSequence expected) {
        return new SameCondition(expected);
    }
}

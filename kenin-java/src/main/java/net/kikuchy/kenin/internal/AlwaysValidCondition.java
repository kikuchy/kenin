package net.kikuchy.kenin.internal;


import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;

/**
 * Created by hiroshi.kikuchi on 2016/02/04.
 */
public class AlwaysValidCondition<V, E> implements Condition<V, E> {
    @Override
    public ValidationResult<E> validate(V value) {
        return new ValidationResult<>(true, new ArrayList<ErrorReason<E>>());
    }
}

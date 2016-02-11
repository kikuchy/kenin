package net.kikuchy.kenin.condition;

import net.kikuchy.kenin.result.ValidationResult;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public interface Condition<V, E> {
    ValidationResult<E> validate(V value);
}

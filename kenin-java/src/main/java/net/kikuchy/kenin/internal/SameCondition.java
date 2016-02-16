package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * This condition requires same value with the given value.
 */
public abstract class SameCondition<V, E> implements Condition<V, E> {
    private final LazyGetter<V> expected;
    private final E message;

    public SameCondition(final V expected, final E errorReason) {
        this.expected = new LazyGetter<V>() {
            @Override
            public V get() {
                return expected;
            }
        };
        this.message = errorReason;
    }

    public SameCondition(LazyGetter<V> expected, final E errorReason) {
        this.expected = expected;
        this.message = errorReason;
    }

    @Override
    public ValidationResult<E> validate(V value) {
        boolean isValid = equalsBetween(value, expected.get());
        List<E> errors = new ArrayList<>();
        if (!isValid) {
            errors.add(message);
        }
        return new ValidationResult<>(isValid, errors);
    }

    protected abstract boolean equalsBetween(V v1, V v2);

    public interface LazyGetter<T> {
        T get();
    }
}

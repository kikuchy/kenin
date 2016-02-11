package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * This condition requires same value with the given value.
 */
public class SameCondition<E> implements Condition<CharSequence, E> {
    private final CharSequence expected;
    private final ErrorReason<E> message;

    public SameCondition(CharSequence expected, final E errorReason) {
        this.expected = expected;
        this.message = new ErrorReason<E>() {
            @Override
            public E getReason() {
                return errorReason;
            }
        };
    }

    @Override
    public ValidationResult<E> validate(CharSequence value) {
        boolean isValid = value.toString().equals(expected.toString());
        List<ErrorReason<E>> errors = new ArrayList<>();
        if (!isValid) {
            errors.add(message);
        }
        return new ValidationResult<>(isValid, errors);
    }
}

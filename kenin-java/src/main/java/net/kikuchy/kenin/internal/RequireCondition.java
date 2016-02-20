package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * This condition requires a value.
 */
public class RequireCondition<E> implements Condition<CharSequence, E> {
    private final E message;

    public RequireCondition(final E errorReason) {
        this.message = errorReason;
    }

    @Override
    public ValidationResult<E> validate(CharSequence value) {
        List<E> errors = new ArrayList<>();
        boolean isValid = value.length() != 0;
        if (!isValid)
            errors.add(message);
        return new ValidationResult<>(isValid, errors);
    }
}

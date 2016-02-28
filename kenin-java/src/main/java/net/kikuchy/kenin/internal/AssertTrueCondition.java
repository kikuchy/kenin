package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kikuchy on 16/02/21.
 */
public class AssertTrueCondition<E> implements Condition<Boolean, E> {
    private final E message;

    public AssertTrueCondition(E errorMessage) {
        this.message = errorMessage;
    }

    @Override
    public ValidationResult<E> validate(Boolean value) {
        List<E> errors = new ArrayList<>();
        boolean isValid = value;
        if (!isValid)
            errors.add(message);
        return new ValidationResult<>(isValid, errors);
    }
}

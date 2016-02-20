package net.kikuchy.kenin.condition;

import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * A variety of {@link net.kikuchy.kenin.condition.Condition} that is useful for testing purpose.
 * Validation status (success or fail) can change any timing you like.
 */
public final class TestCondition<V> implements Condition<V, String> {
    private boolean isValid = true;

    /**
     * Set next validation result is succeeded.
     *
     * @param valid If {@code true}, next validation will success. {@code false} will make failed.
     */
    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public ValidationResult<String> validate(V value) {
        List<String> errors = new ArrayList<>();
        errors.add("test");
        return new ValidationResult<>(isValid, errors);
    }
}

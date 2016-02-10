package net.kikuchy.kenin.condition;

import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ValidationResult;

/**
 * A variety of {@link net.kikuchy.kenin.condition.Condition} that is useful for testing purpose.
 * Validation status (success or fail) can change any timing you like.
 */
public final class TestCondition<T> implements net.kikuchy.kenin.condition.Condition<T> {
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
    public net.kikuchy.kenin.result.ValidationResult validate(T value) {
        net.kikuchy.kenin.result.ErrorMessageCollection errors = new net.kikuchy.kenin.result.ErrorMessageCollection();
        errors.add(new ErrorReason() {
            @Override
            public String toString() {
                return "test";
            }
        });
        return new ValidationResult(isValid, errors);
    }
}

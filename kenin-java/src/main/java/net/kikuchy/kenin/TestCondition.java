package net.kikuchy.kenin;

/**
 * A variety of {@link Condition} that is useful for testing purpose.
 * Validation status (success or fail) can change any timing you like.
 */
public final class TestCondition<T> implements Condition<T> {
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
    public ValidationResult validate(T value) {
        ErrorMessageCollection errors = new ErrorMessageCollection();
        errors.add(new ErrorMessage() {
            @Override
            public String toString() {
                return "test";
            }
        });
        return new ValidationResult(isValid, errors);
    }
}

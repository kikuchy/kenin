package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ErrorMessageCollection;
import net.kikuchy.kenin.result.ValidationResult;

/**
 * This condition requires a value.
 */
public class RequireCondition implements Condition<CharSequence> {
    private final static String DEFAULT_MESSAGE = "This field is required.";

    private final ErrorReason message;

    public RequireCondition(final String errorMessage) {
        this.message = new ErrorReason() {
            @Override
            public String toString() {
                return errorMessage;
            }
        };
    }

    public RequireCondition() {
        this(DEFAULT_MESSAGE);
    }

    @Override
    public ValidationResult validate(CharSequence value) {
        ErrorMessageCollection errors = new ErrorMessageCollection();
        boolean isValid = value.length() != 0;
        if (!isValid)
            errors.add(message);
        return new ValidationResult(isValid, errors);
    }
}

package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.Condition;
import net.kikuchy.kenin.ErrorMessage;
import net.kikuchy.kenin.ErrorMessageCollection;
import net.kikuchy.kenin.ValidationResult;

/**
 * This condition requires a value.
 */
public class RequireCondition implements Condition<CharSequence> {
    private final static String DEFAULT_MESSAGE = "This field is required.";

    private final ErrorMessage message;

    public RequireCondition(final String errorMessage) {
        this.message = new ErrorMessage() {
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

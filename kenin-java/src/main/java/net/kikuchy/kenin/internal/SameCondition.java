package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ErrorMessageCollection;
import net.kikuchy.kenin.result.ValidationResult;

/**
 * This condition requires same value with the given value.
 */
public class SameCondition implements Condition<CharSequence> {
    private final static String DEFAULT_MESSAGE_FORMAT = "Value must be same with \"%s\"";

    private final CharSequence expected;
    private final ErrorReason message;

    public SameCondition(CharSequence expected) {
        this(expected, String.format(DEFAULT_MESSAGE_FORMAT, expected));
    }

    public SameCondition(CharSequence expected, final String errorMessage) {
        this.expected = expected;
        this.message = new ErrorReason() {
            @Override
            public String toString() {
                return errorMessage;
            }
        };
    }

    @Override
    public ValidationResult validate(CharSequence value) {
        boolean isValid = value.toString().equals(expected.toString());
        ErrorMessageCollection errors = new ErrorMessageCollection();
        if (!isValid) {
            errors.add(message);
        }
        return new ValidationResult(isValid, errors);
    }
}

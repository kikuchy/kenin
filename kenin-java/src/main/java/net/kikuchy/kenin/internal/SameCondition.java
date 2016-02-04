package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.Condition;
import net.kikuchy.kenin.ErrorMessage;
import net.kikuchy.kenin.ErrorMessageCollection;
import net.kikuchy.kenin.ValidationResult;

/**
 * This condition requires same value with the given value.
 */
public class SameCondition implements Condition<CharSequence> {
    private final static String DEFAULT_MESSAGE_FORMAT = "Value must be same with \"%s\"";

    private final CharSequence expected;
    private final ErrorMessage message;

    public SameCondition(CharSequence expected) {
        this(expected, String.format(DEFAULT_MESSAGE_FORMAT, expected));
    }

    public SameCondition(CharSequence expected, final String errorMessage) {
        this.expected = expected;
        this.message = new ErrorMessage() {
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

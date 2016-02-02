package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.Condition;
import net.kikuchy.kenin.ErrorMessage;
import net.kikuchy.kenin.ErrorMessageCollection;
import net.kikuchy.kenin.ValidationResult;

/**
 * Created by kikuchy on 16/02/03.
 */
public class RequireCondition implements Condition<CharSequence> {
    private final static ErrorMessage MESSAGE = new ErrorMessage() {
        @Override
        public String toString() {
            return "This field is required.";
        }
    };

    @Override
    public ValidationResult validate(CharSequence value) {
        ErrorMessageCollection errors = new ErrorMessageCollection();
        boolean isValid = value.length() != 0;
        if (!isValid)
            errors.add(MESSAGE);
        return new ValidationResult(isValid, errors);
    }
}

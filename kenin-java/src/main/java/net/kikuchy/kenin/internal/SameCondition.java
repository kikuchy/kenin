package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.Condition;
import net.kikuchy.kenin.ErrorMessage;
import net.kikuchy.kenin.ErrorMessageCollection;
import net.kikuchy.kenin.ValidationResult;

/**
 * Created by hiroshi.kikuchi on 2016/02/03.
 */
public class SameCondition implements Condition<CharSequence> {
    private CharSequence expected;

    public SameCondition(CharSequence expected) {
        this.expected = expected;
    }

    @Override
    public ValidationResult validate(CharSequence value) {
        boolean isValid = value.toString().equals(expected.toString());
        ErrorMessageCollection errors = new ErrorMessageCollection();
        if (!isValid) {
            errors.add(new ErrorMessage() {
                @Override
                public String toString() {
                    return String.format("Value must be same with %s", expected);
                }
            });
        }
        return new ValidationResult(isValid, errors);
    }
}

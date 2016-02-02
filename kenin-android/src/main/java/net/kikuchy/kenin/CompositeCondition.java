package net.kikuchy.kenin;

import java.util.List;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public abstract class CompositeCondition<T> extends Condition<T> {
    protected Condition<T> right;
    protected Condition<T> left;

    protected CompositeCondition(Condition<T> right, Condition<T> left) {
        this.right = right;
        this.left = left;
    }

    public static Condition<T> and(Condition... conditions) {
        if (conditions.length == 0) {
            throw new IllegalArgumentException("CompositCondition must have least 2 condisions");
        } else if (conditions.length == 1) {
            return conditions[0];
        } else if (conditions.length == 2) {
            return new CompositeCondition<T>(conditions[0], conditions[1]) {
                @Override
                public ValidationResult validate(T value) {
                    ValidationResult a = right.validate(value);
                    ValidationResult b = left.validate(value);
                    boolean isValid = a.isValid() && b.isValid();
                    if (isValid) {
                        return a;
                    } else {
                        List<String> errors = a.getMessages();
                        errors.addAll(b.getMessages());
                        return new ValidationResult<>(isValid, errors);
                    }
                    return null;
                }
            };
        }
    }

    public static CompositeCondition or(Condition... conditions) {

    }
}

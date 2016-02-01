package kikuchy.net.kenin;

import java.util.List;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public abstract class CompositeCondition<T, E> extends Condition<T, E> {
    protected Condition<T, E> right;
    protected Condition<T, E> left;

    protected CompositeCondition(Condition<T, E> right, Condition<T, E> left) {
        this.right = right;
        this.left = left;
    }

    public static Condition<T, E> and(Condition... conditions) {
        if (conditions.length == 0) {
            throw new IllegalArgumentException("CompositCondition must have least 2 condisions");
        } else if (conditions.length == 1) {
            return conditions[0];
        } else if (conditions.length == 2) {
            return new CompositeCondition<T, E>(conditions[0], conditions[1]) {
                @Override
                public ValidationResult<E> validate(T value) {
                    ValidationResult<E> a = right.validate(value);
                    ValidationResult<E> b = left.validate(value);
                    boolean isValid = a.isValid() && b.isValid();
                    if (isValid) {
                        return a;
                    } else {
                        List<E> errors = a.getErrorMessages();
                        errors.addAll(b.getErrorMessages());
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

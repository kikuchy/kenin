package net.kikuchy.kenin.condition;

import net.kikuchy.kenin.internal.AlwaysValidCondition;
import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public abstract class CompositeCondition<V, E> implements Condition<V, E> {
    protected Condition<V, E> right;
    protected Condition<V, E> left;

    protected CompositeCondition(Condition<V, E> right, Condition<V, E> left) {
        this.right = right;
        this.left = left;
    }

    protected abstract boolean binaryOperate(ValidationResult<E> right, ValidationResult<E> left);

    @Override
    public ValidationResult<E> validate(V value) {
        ValidationResult<E> rightResult = right.validate(value);
        ValidationResult<E> leftResult = left.validate(value);
        boolean isValid = binaryOperate(rightResult, leftResult);
        List<ErrorReason<E>> errors = new ArrayList<>(rightResult.getReasons());
        if (!isValid) {
            errors.addAll(leftResult.getReasons());
        }
        return new ValidationResult<>(isValid, errors);
    }

    public static <V, E> Condition<V, E> and() {
        return new AlwaysValidCondition<>();
    }

    @SafeVarargs
    public static <V, E> Condition<V, E> and(Condition<V, E>... conditions) {
        return and(0, conditions);
    }

    @SafeVarargs
    private static <V, E> Condition<V, E> and(int offset, Condition<V, E>... conditions) {
        if (conditions.length - offset < 1) {
            throw new IllegalArgumentException("This method must receive least of one arguments.");
        } else if (conditions.length - offset == 1) {
            return conditions[offset];
        } else {
            return new CompositeCondition<V, E>(conditions[offset], and(offset + 1, conditions)) {
                @Override
                protected boolean binaryOperate(ValidationResult<E> right, ValidationResult<E> left) {
                    return right.isValid() && left.isValid();
                }
            };
        }
    }

    public static <V, E> Condition<V, E> or() {
        return new AlwaysValidCondition<>();
    }

    @SafeVarargs
    public static <V, E> Condition<V, E> or(Condition<V, E>... conditions) {
        return or(0, conditions);
    }

    @SafeVarargs
    private static <V, E> Condition<V, E> or(int offset, Condition<V, E>... conditions) {
        if (conditions.length - offset < 1) {
            throw new IllegalArgumentException("This method must receive least of one arguments.");
        } else if (conditions.length - offset == 1) {
            return conditions[offset];
        } else {
            return new CompositeCondition<V, E>(conditions[offset], or(offset + 1, conditions)) {
                @Override
                protected boolean binaryOperate(ValidationResult<E> right, ValidationResult<E> left) {
                    return right.isValid() || left.isValid();
                }
            };
        }
    }
}

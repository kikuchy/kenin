package net.kikuchy.kenin.internal;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ErrorReason;
import net.kikuchy.kenin.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class TextLengthCondition<E> implements Condition<CharSequence, E> {
    private final ErrorReason<E> message;
    private int length;
    private Comparison compareWay;

    public TextLengthCondition(int length, Comparison compareWay, final E errorReason) {
        this.message = new ErrorReason<E>() {
            @Override
            public E getReason() {
                return errorReason;
            }
        };
        this.length = length;
        this.compareWay = compareWay;
    }

    @Override
    public ValidationResult<E> validate(CharSequence value) {
        List<ErrorReason<E>> errors = new ArrayList<>();
        boolean isValid = false;
        switch (compareWay) {
            case EQUAL:
                isValid = value.length() == length;
                break;
            case LESS_THAN:
                isValid = value.length() < length;
                break;
            case LESS_THAN_OR_EQUAL:
                isValid = value.length() <= length;
                break;
            case GREATER_THAN:
                isValid = value.length() > length;
                break;
            case GREATER_THAN_OR_EQUAL:
                isValid = value.length() >= length;
                break;
        }
        if (!isValid)
            errors.add(message);
        return new ValidationResult<>(isValid, errors);
    }

    public enum Comparison {
        EQUAL,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
    }
}

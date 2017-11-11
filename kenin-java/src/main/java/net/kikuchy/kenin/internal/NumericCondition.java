package net.kikuchy.kenin.internal;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class NumericCondition<E> extends PartialPatternMatchCondition<E> {
    public NumericCondition(E errorReason) {
        super("^\\d*$", errorReason);
    }
}

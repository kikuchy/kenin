package net.kikuchy.kenin.internal;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class AlphaNumericCondition<E> extends PartialPatternMatchCondition<E> {
    public AlphaNumericCondition(E errorReason) {
        super("^[a-zA-Z0-9]*$", errorReason);
    }
}

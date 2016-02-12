package net.kikuchy.kenin.internal;

/**
 * Created by hiroshi.kikuchi on 2016/02/12.
 */
public class AlphabetCondition<E> extends PatternMatchCondition<E> {
    public AlphabetCondition(E errorReason) {
        super("^[a-zA-Z]+$", errorReason);
    }
}

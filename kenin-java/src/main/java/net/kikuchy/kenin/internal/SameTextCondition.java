package net.kikuchy.kenin.internal;

/**
 * Created by kikuchy on 16/02/12.
 */
public class SameTextCondition<E> extends SameCondition<CharSequence, E> {
    public SameTextCondition(CharSequence expected, E errorReason) {
        super(expected, errorReason);
    }

    public SameTextCondition(LazyGetter<CharSequence> expected, E errorReason) {
        super(expected, errorReason);
    }

    @Override
    protected boolean equalsBetween(CharSequence v1, CharSequence v2) {
        return v1.toString().equals(v2.toString());
    }
}

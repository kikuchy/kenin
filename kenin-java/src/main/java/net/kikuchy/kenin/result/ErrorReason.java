package net.kikuchy.kenin.result;

/**
 * Created by kikuchy on 16/02/02.
 */
public abstract class ErrorReason<E> {
    public abstract E getReason();

    @Override
    public boolean equals(Object obj) {
        return getReason().equals(obj);
    }
}

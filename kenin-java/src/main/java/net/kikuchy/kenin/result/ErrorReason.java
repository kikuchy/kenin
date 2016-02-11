package net.kikuchy.kenin.result;

/**
 * Created by kikuchy on 16/02/02.
 */
public abstract class ErrorReason<E> {
    public abstract E getReason();

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (obj instanceof ErrorReason) {
            ErrorReason reason = (ErrorReason) obj;
            return reason.getReason().equals(getReason());
        }

        return false;
    }
}

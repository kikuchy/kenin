package net.kikuchy.kenin.result;

import java.util.List;

/**
 * Created by kikuchy on 16/02/03.
 */
public interface ResultReceiver<E> {
    void validationSucceeded();
    void validationFailed(List<E> errorReasons);
}

package net.kikuchy.kenin.result;

import java.util.List;

/**
 * Notify validation result if new validation result changes from the last result.
 */
public class CachedResultReceiver<E> implements ResultReceiver<E> {
    private boolean isLastValidationSucceeded = false;
    private List<ErrorReason<E>> lastErrorMessages;
    private ResultReceiver<E> trueReceiver;

    public CachedResultReceiver(ResultReceiver<E> receiver) {
        this.trueReceiver = receiver;
    }

    @Override
    public void validationSucceeded() {
        if (isLastValidationSucceeded != true) {
            isLastValidationSucceeded = true;
            trueReceiver.validationSucceeded();
        }
    }

    @Override
    public void validationFailed(List<ErrorReason<E>> errorReasons) {
        if (isLastValidationSucceeded != false ||
                !errorReasons.equals(lastErrorMessages)) {
            isLastValidationSucceeded = false;
            lastErrorMessages = errorReasons;
            trueReceiver.validationFailed(errorReasons);
        }
    }
}

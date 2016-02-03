package net.kikuchy.kenin;

import java.util.List;

/**
 * Notify validation result if new validation result changes from the last result.
 */
public class CachedResultReceiver implements ResultReceiver {
    private boolean isLastValidationSucceeded = false;
    private List<String> lastErrorMessages;
    private ResultReceiver trueReceiver;

    public CachedResultReceiver(ResultReceiver receiver) {
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
    public void validationFailed(List<String> errorMessages) {
        if (isLastValidationSucceeded != false ||
                !errorMessages.equals(lastErrorMessages)) {
            isLastValidationSucceeded = false;
            lastErrorMessages = errorMessages;
            trueReceiver.validationFailed(errorMessages);
        }
    }
}

package net.kikuchy.kenin.result;

import java.util.List;

/**
 * Created by kikuchy on 16/02/02.
 */
public class ValidationResult<E> {
    private boolean isValid = false;
    private List<ErrorReason<E>> errorReasons;

    public ValidationResult(boolean isValid, List<ErrorReason<E>> errorReasons) {
        this.isValid = isValid;
        this.errorReasons = errorReasons;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<ErrorReason<E>> getReasons() {
        return errorReasons;
    }
}

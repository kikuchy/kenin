package net.kikuchy.kenin.result;

import java.util.List;

/**
 * Created by kikuchy on 16/02/02.
 */
public class ValidationResult<E> {
    private boolean isValid = false;
    private List<E> errorReasons;

    public ValidationResult(boolean isValid, List<E> errorReasons) {
        this.isValid = isValid;
        this.errorReasons = errorReasons;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<E> getReasons() {
        return errorReasons;
    }
}

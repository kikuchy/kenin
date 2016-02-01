package kikuchy.net.kenin;

import java.util.List;

/**
 * Created by kikuchy on 16/02/02.
 */
public class ValidationResult<E> {
    private boolean isValid = false;
    private List<E> errorMessages;

    public ValidationResult(boolean isValid, List<E> errorMessages) {
        this.isValid = isValid;
        this.errorMessages = errorMessages;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<E> getErrorMessages() {
        return errorMessages;
    }
}

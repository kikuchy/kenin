package net.kikuchy.kenin.result;

import java.util.List;

/**
 * Created by kikuchy on 16/02/02.
 */
public class ValidationResult {
    private boolean isValid = false;
    private ErrorMessageCollection errorMessages;

    public ValidationResult(boolean isValid, ErrorMessageCollection errorMessages) {
        this.isValid = isValid;
        this.errorMessages = errorMessages;
    }

    public boolean isValid() {
        return isValid;
    }

    public List<String> getMessages() {
        return errorMessages.getMessages();
    }
}

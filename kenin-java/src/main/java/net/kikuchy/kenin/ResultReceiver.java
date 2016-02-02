package net.kikuchy.kenin;

import java.util.List;

/**
 * Created by kikuchy on 16/02/03.
 */
public interface ResultReceiver {
    void validationSucceeded();
    void validationFailed(List<String> errorMessages);
}

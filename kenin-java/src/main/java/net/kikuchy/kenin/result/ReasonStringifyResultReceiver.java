package net.kikuchy.kenin.result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kikuchy on 16/02/17.
 */
public class ReasonStringifyResultReceiver<E> implements ResultReceiver<E> {
    private ResultReceiver<String> trueReceiver;
    private Stringifier<E> stringifier;

    public ReasonStringifyResultReceiver(Stringifier<E> stringifier, ResultReceiver<String> trueReceiver) {
        this.trueReceiver = trueReceiver;
        this.stringifier = stringifier;
    }

    @Override
    public void validationSucceeded() {
        trueReceiver.validationSucceeded();
    }

    @Override
    public void validationFailed(List<E> errorReasons) {
        List<String> reasons = new ArrayList<>();
        for (E rawReason : errorReasons) {
            reasons.add(stringifier.stringify(rawReason));
        }
        trueReceiver.validationFailed(reasons);
    }

    public interface Stringifier<R> {
        String stringify(R reason);
    }
}

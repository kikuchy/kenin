package net.kikuchy.kenin;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ResultReceiver;
import net.kikuchy.kenin.result.ValidationResult;
import net.kikuchy.kenin.trigger.ValueChangedEventEmitter;
import net.kikuchy.kenin.trigger.ValueChangedEventRelay;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class Kenin<V, E> {
    protected ValueChangedEventEmitter<V> emitter = new ValueChangedEventEmitter<V>() {
        @Override
        public void emit(V value) {
            Kenin.this.onValueChanged(value);
        }
    };
    protected Condition<? super V, E> condition;
    protected Set<ResultReceiver<E>> resultReceivers = new HashSet<>();

    protected Kenin(ValueChangedEventRelay<V> relay, Condition<? super V, E> condition) {
        this.condition = condition;
        relay.relay(emitter);
    }

    public void onValueChanged(V value) {
        ValidationResult<E> result = condition.validate(value);
        for (ResultReceiver<E> res : resultReceivers) {
            if (result.isValid()) {
                res.validationSucceeded();
            } else {
                res.validationFailed(result.getReasons());
            }
        }
    }

    public void addResultReceiver(ResultReceiver<E> resultReceiver) {
        resultReceivers.add(resultReceiver);
    }

    public void removeResultReceiver(ResultReceiver<E> resultReceiver) {
        resultReceivers.remove(resultReceiver);
    }

    @NotNull
    public static <V, E> Kenin<V, E> create(ValueChangedEventRelay<V> relay, Condition<? super V, E> condition) {
        return new Kenin<>(relay, condition);
    }
}

package net.kikuchy.kenin;

import net.kikuchy.kenin.condition.Condition;
import net.kikuchy.kenin.result.ResultReceiver;
import net.kikuchy.kenin.result.ValidationResult;
import net.kikuchy.kenin.trigger.ValueChangedEventEmitter;
import net.kikuchy.kenin.trigger.ValueChangedEventRelay;

import java.util.HashSet;
import java.util.Set;

public class Kenin<V, E> {
    protected ValueChangedEventEmitter<V> emitter = new ValueChangedEventEmitter<V>() {
        @Override
        public void emit(V value) {
            Kenin.this.onValueChanged(value);
        }
    };
    protected Condition<V, E> condition;
    protected Set<ResultReceiver<E>> resultReceivers;

    protected Kenin(Builder<V, E> builder) {
        this.condition = builder.condition;
        this.resultReceivers = builder.resultReceivers;
        builder.relay.relay(emitter);
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

    public static <V, E> Builder<V, E> builder(ValueChangedEventRelay<V> relay) {
        return new Builder<>(relay);
    }

    public static class Builder<V, E> {
        private ValueChangedEventRelay<V> relay;
        private Condition<V, E> condition;
        private HashSet<ResultReceiver<E>> resultReceivers = new HashSet<>();

        protected Builder(ValueChangedEventRelay<V> relay) {
            this.relay = relay;
        }

        public Builder<V, E> setCondition(Condition<V, E> condition) {
            this.condition = condition;
            return this;
        }

        public Builder<V, E> addResultReceiver(ResultReceiver<E> resultReceiver) {
            this.resultReceivers.add(resultReceiver);
            return this;
        }

        public Kenin<V, E> build() {
            return new Kenin<>(this);
        }
    }
}

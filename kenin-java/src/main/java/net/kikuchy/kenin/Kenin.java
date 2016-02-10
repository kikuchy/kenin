package net.kikuchy.kenin;

import net.kikuchy.kenin.result.ValidationResult;
import net.kikuchy.kenin.trigger.ValueChangedEventRelay;

import java.util.HashSet;
import java.util.Set;

public class Kenin<T> {
    protected net.kikuchy.kenin.trigger.ValueChangedEventEmitter<T> emitter = new net.kikuchy.kenin.trigger.ValueChangedEventEmitter<T>() {
        @Override
        public void emit(T value) {
            Kenin.this.onValueChanged(value);
        }
    };
    protected net.kikuchy.kenin.condition.Condition<T> condition;
    protected Set<net.kikuchy.kenin.result.ResultReceiver> resultReceivers;

    protected Kenin(Builder<T> builder) {
        this.condition = builder.condition;
        this.resultReceivers = builder.resultReceivers;
        builder.relay.relay(emitter);
    }

    public void onValueChanged(T value) {
        ValidationResult result = condition.validate(value);
        for (net.kikuchy.kenin.result.ResultReceiver res : resultReceivers) {
            if (result.isValid()) {
                res.validationSucceeded();
            } else {
                res.validationFailed(result.getReasons());
            }
        }
    }

    public static <T> Builder<T> builder(net.kikuchy.kenin.trigger.ValueChangedEventRelay<T> relay) {
        return new Builder<>(relay);
    }

    public static class Builder<T> {
        private net.kikuchy.kenin.trigger.ValueChangedEventRelay<T> relay;
        private net.kikuchy.kenin.condition.Condition<T> condition;
        private HashSet<net.kikuchy.kenin.result.ResultReceiver> resultReceivers = new HashSet<>();

        protected Builder(ValueChangedEventRelay<T> relay) {
            this.relay = relay;
        }

        public Builder<T> setCondition(net.kikuchy.kenin.condition.Condition<T> condition) {
            this.condition = condition;
            return this;
        }

        public Builder<T> addResultReceiver(net.kikuchy.kenin.result.ResultReceiver resultReceiver) {
            this.resultReceivers.add(resultReceiver);
            return this;
        }

        public Kenin<T> build() {
            return new Kenin<>(this);
        }
    }
}

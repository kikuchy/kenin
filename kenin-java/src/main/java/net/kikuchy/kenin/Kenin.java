package net.kikuchy.kenin;

import java.util.HashSet;
import java.util.Set;

public class Kenin<T> {
    protected ValueChangedEventEmitter<T> emitter = new ValueChangedEventEmitter<T>() {
        @Override
        public void emit(T value) {
            Kenin.this.onValueChanged(value);
        }
    };
    protected Condition<T> condition;
    protected Set<ResultReceiver> resultReceivers;

    protected Kenin(Builder<T> builder) {
        this.condition = builder.condition;
        this.resultReceivers = builder.resultReceivers;
        builder.relay.relay(emitter);
    }

    public void onValueChanged(T value){
        ValidationResult result = condition.validate(value);
        for (ResultReceiver res: resultReceivers) {
            if (result.isValid()) {
                res.validationSucceeded();
            } else {
                res.validationFailed(result.getMessages());
            }
        }
    }

    public static <T> Builder<T> builder(ValueChangedEventRelay<T> relay) {
        Builder<T> builder = new Builder<>();
        builder.relay = relay;
        return builder;
    }

    public static class Builder<T> {
        private ValueChangedEventRelay<T> relay;
        private Condition<T> condition;
        private HashSet<ResultReceiver> resultReceivers = new HashSet<>();

        protected Builder(){}

        public Builder<T> setCondition(Condition<T> condition) {
            this.condition = condition;
            return this;
        }

        public Builder<T> addResultReceiver(ResultReceiver resultReceiver) {
            this.resultReceivers.add(resultReceiver);
            return this;
        }
        public Kenin<T> build() {
            return new Kenin<>(this);
        }
    }
}

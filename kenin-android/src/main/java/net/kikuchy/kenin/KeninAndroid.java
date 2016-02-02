package net.kikuchy.kenin;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public class KeninAndroid<T> {
    protected Condition validationCondition;
    protected Set<ResultReceiver> resultReceivers;
    protected ValueChangedEventEmitter<T> eventEmitter;

    protected KeninAndroid(Builder builder) {
        validationCondition = builder.condition;
        resultReceivers = builder.resultReceivers;
        eventEmitter = builder.eventEmitter;
        eventEmitter.setEventListener(this);
    }

    public void onValueChanged(T value) {

    }

    public static Builder builder() {
        Builder builder = new Builder();
        return builder;
    }

    public static class Builder {
        private Condition condition;
        private HashSet<ResultReceiver> resultReceivers = new HashSet<>();
        private ValueChangedEventEmitter<?> eventEmitter;

        public Builder condition(Condition condition) {
            this.condition = condition;
            return this;
        }

        public Builder addResultReceiver(ResultReceiver resultReceiver) {
            this.resultReceivers.add(resultReceiver);
            return this;
        }

        public KeninAndroid build() {
            return new KeninAndroid(this);
        }

        public Builder setEventEmitter(ValueChangedEventEmitter eventEmitter) {
            this.eventEmitter = eventEmitter;
            return this;
        }
    }

    public interface ResultReceiver {
        void validationSucceeded();
        void validationFailed();
    }

    protected static class ValueChangedEventEmitter<T> {
        private KeninAndroid<T> listener;

        public void emit(T value) {
            if (listener != null) {
                listener.onValueChanged(value);
            }
        }

        public void setEventListener(KeninAndroid<T> keninAndroid) {
            listener = keninAndroid;
        }
    }
}

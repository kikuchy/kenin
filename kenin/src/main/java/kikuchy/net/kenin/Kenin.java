package kikuchy.net.kenin;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public class Kenin<T> {
    protected Condition validationCondition;
    protected Set<ResultReceiver> resultReceivers;
    protected ValueChangedEventEmitter<T> eventEmitter;

    protected Kenin(Builder builder) {
        validationCondition = builder.condition;
        resultReceivers = builder.resultReceivers;
        eventEmitter = builder.eventEmitter;
        eventEmitter.setEventListener(this);
    }

    public void onValueChanged(T value) {

    }

    public static Builder builder(EditText editText) {
        Builder<EditText> builder = new Builder<>();
        builder.view = editText;
        final ValueChangedEventEmitter<CharSequence> emitter = new ValueChangedEventEmitter<>();    //TODO; ここにどうやってバリデーター本体を繋げるか
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emitter.emit(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        builder.setEventEmitter(emitter);
        return builder;
    }

    public static class Builder<V extends View> {
        private Condition condition;
        private HashSet<ResultReceiver> resultReceivers = new HashSet<>();
        private ValueChangedEventEmitter<?> eventEmitter;
        private V view;

        public Builder<V> condition(Condition condition) {
            this.condition = condition;
            return this;
        }

        public Builder<V> addResultReceiver(ResultReceiver resultReceiver) {
            this.resultReceivers.add(resultReceiver);
            return this;
        }

        public Kenin build() {
            return new Kenin(this);
        }

        public Builder<V> setEventEmitter(ValueChangedEventEmitter eventEmitter) {
            this.eventEmitter = eventEmitter;
            return this;
        }
    }

    public interface ResultReceiver {
        void validationSucceeded();
        void validationFailed();
    }

    protected static class ValueChangedEventEmitter<T> {
        private Kenin<T> listener;

        public void emit(T value) {
            if (listener != null) {
                listener.onValueChanged(value);
            }
        }

        public void setEventListener(Kenin<T> kenin) {
            listener = kenin;
        }
    }
}

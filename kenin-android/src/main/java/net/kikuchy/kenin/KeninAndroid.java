package net.kikuchy.kenin;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.List;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public class KeninAndroid<T> extends Kenin<T> {
    protected Context context;

    protected KeninAndroid(Builder<T> builder) {
        super(builder);
        this.context = builder.context;
    }

    public static Kenin.Builder<CharSequence> builder(final EditText editText) {
        return new Builder<>(new ValueChangedEventRelay<CharSequence>() {
            @Override
            public void relay(final ValueChangedEventEmitter<CharSequence> emitter) {
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
            }
        }, editText.getContext());
    }

    public static Kenin.Builder<CharSequence> builder(final TextInputLayout textInputLayout) {
        return builder(textInputLayout.getEditText())
                .addResultReceiver(new ResultReceiver() {
                    @Override
                    public void validationSucceeded() {
                        textInputLayout.setError(null);
                    }

                    @Override
                    public void validationFailed(List<String> errorMessages) {
                        StringBuilder builder = new StringBuilder();
                        for (String err : errorMessages) {
                            if (builder.length() != 0) {
                                builder.append('\n');
                            }
                            builder.append(err);
                        }
                        textInputLayout.setError(builder.toString());
                    }
                });
    }

    public static class Builder<T> extends Kenin.Builder<T> {
        private Context context;

        protected Builder(ValueChangedEventRelay<T> relay, Context context) {
            super(relay);
            this.context = context;
        }

        public Builder<T> setContext(Context context) {
            this.context = context;
            return this;
        }
    }
}

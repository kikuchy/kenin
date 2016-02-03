package net.kikuchy.kenin;

/**
 * A variety of {@link ValueChangedEventRelay} that is useful for testing purpose.
 * You can emmit valueChange event any timing you like.
 */
public final class TestValueChangedEventRelay<T> implements ValueChangedEventRelay<T> {
    private ValueChangedEventEmitter<T> emitter;

    @Override
    public void relay(ValueChangedEventEmitter<T> emitter) {
        this.emitter = emitter;
    }

    /**
     * Emmit valueChange event with any value.
     *
     * @param value A value will be validated.
     */
    public void emmit(T value) {
        emitter.emit(value);
    }
}

package net.kikuchy.kenin.trigger;

/**
 * Created by kikuchy on 16/02/03.
 */
public interface ValueChangedEventRelay<T> {
    void relay(final ValueChangedEventEmitter<T> emitter);
}

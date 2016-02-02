package net.kikuchy.kenin;

/**
 * Created by kikuchy on 16/02/03.
 */
public interface ValueChangedEventEmitter<T> {
    void emit(T value);
}

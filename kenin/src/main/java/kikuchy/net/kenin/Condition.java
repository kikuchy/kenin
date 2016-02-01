package kikuchy.net.kenin;

/**
 * Created by hiroshi.kikuchi on 2016/02/01.
 */
public abstract class Condition<T, E> {
    public abstract ValidationResult<E> validate(T value);
}

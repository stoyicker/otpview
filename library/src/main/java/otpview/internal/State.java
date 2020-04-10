package otpview.internal;

public abstract class State<T> {
  final T value;

  State(final T _value) {
    value = _value;
  }

  public T getValue() {
    return value;
  }
}

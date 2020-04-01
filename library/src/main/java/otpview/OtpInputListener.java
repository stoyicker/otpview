package otpview;

import android.annotation.SuppressLint;
import android.util.SparseArray;

/**
 * Describes a listener for input changes that can be registered on an instance of {@link OtpView}
 * via {@link OtpView#registerOtpInputListener(OtpInputListener)}. For an example implementation
 * that is only invoked when all boxes are filled-in see {@link SimpleOtpInputListener}
 *
 * @see OtpView#registerOtpInputListener(OtpInputListener)
 * @see OtpView#unregisterOtpInputListener(OtpInputListener)
 * @see SimpleOtpInputListener
 */
@SuppressLint("UnknownNullness") // It is an interface, it cannot describe the implementation
public interface OtpInputListener {
  /**
   * Notifies that the current text of the view has potentially changed. Note how this does not
   * mean that the user has typed something new, as this may be triggered by state restoration,
   * addition and removal of boxes, the text setters in {@link OtpView}, etc.
   * Implementations will always be called on the UI thread.
   *
   * @param textRaw The current text in the view
   * @param boxAmount The amount of boxes currently in the view. You can compare this to the size
   *                  of the {@link SparseArray} to tell if the user may already have finished input
   */
  void onInput(SparseArray<Character> textRaw, int boxAmount);
}

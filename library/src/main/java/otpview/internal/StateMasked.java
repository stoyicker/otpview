package otpview.internal;

import android.content.res.TypedArray;
import android.util.TypedValue;
import android.widget.TextView;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateMasked extends State<Boolean> implements Applicable {
  private final OtpView otpView;

  public StateMasked(final OtpView _otpView, boolean mask) {
    super(mask);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    final int childCount = otpView.getChildCount();
    final float newTextSize = value ? 0 : otpView.getBoxTextSize();
    for (int i = 0; i < childCount; i++) {
      ((TextView) otpView.getChildAt(i)).setTextSize(TypedValue.COMPLEX_UNIT_SP, newTextSize);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateMasked create(final OtpView otpView, final TypedArray typedArray) {
      final boolean mask = typedArray.getBoolean(
          R.styleable.OtpView_otp_masked,
          otpView.getContext().getResources().getBoolean(R.bool.default_masked));
      return new StateMasked(otpView, mask);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

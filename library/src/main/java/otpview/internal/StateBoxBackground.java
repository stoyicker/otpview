package otpview.internal;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxBackground extends State<Drawable> implements Applicable {
  private final OtpView otpView;

  public StateBoxBackground(final OtpView _otpView, final Drawable backgroundDrawable) {
    super(backgroundDrawable);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    final int childCount = otpView.getChildCount();
    if (value == null) {
      for (int i = 0; i < childCount; i++) {
        otpView.getChildAt(i).setBackground(null);
      }
      return;
    }
    final Drawable.ConstantState valueConstantState = value.getConstantState();
    if (valueConstantState == null) {
      return;
    }
    for (int i = 0; i < childCount; i++) {
      otpView.getChildAt(i).setBackground(valueConstantState.newDrawable());
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxBackground create(final OtpView otpView, final TypedArray typedArray) {
      return new StateBoxBackground(otpView, typedArray.getDrawable(
          R.styleable.OtpView_otp_boxBackground));
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

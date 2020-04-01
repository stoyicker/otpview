package otpview.internal;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.widget.TextView;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxTextColor extends State<ColorStateList> implements Applicable {
  private final OtpView otpView;

  public StateBoxTextColor(final OtpView _otpView, final ColorStateList colorStateList) {
    super(colorStateList);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    int childCount = otpView.getChildCount();
    for (int i = 0; i < childCount; i++) {
      ((TextView) otpView.getChildAt(i)).setTextColor(value);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxTextColor create(final OtpView otpView, final TypedArray typedArray) {
      return new StateBoxTextColor(otpView, typedArray.getColorStateList(
          R.styleable.OtpView_otp_boxTextColor));
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

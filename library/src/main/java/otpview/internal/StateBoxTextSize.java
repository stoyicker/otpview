package otpview.internal;

import android.content.res.TypedArray;
import android.util.TypedValue;
import android.widget.TextView;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxTextSize extends State<Float> implements Applicable {
  private final OtpView otpView;

  public StateBoxTextSize(final OtpView _otpView, float textSize) {
    super(textSize);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    int childCount = otpView.getChildCount();
    for (int i = 0; i < childCount; i++) {
      ((TextView) otpView.getChildAt(i)).setTextSize(TypedValue.COMPLEX_UNIT_SP, value);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxTextSize create(final OtpView otpView, final TypedArray typedArray) {
      final float textSize = typedArray.getDimension(
          R.styleable.OtpView_otp_boxTextSize, otpView.getContext().getResources().getDimension(
              R.dimen.default_boxTextSize));
      return new StateBoxTextSize(otpView, textSize);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

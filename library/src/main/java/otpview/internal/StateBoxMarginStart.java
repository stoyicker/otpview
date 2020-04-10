package otpview.internal;

import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Px;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxMarginStart extends State<Integer> implements Applicable {
  private final OtpView otpView;

  public StateBoxMarginStart(final OtpView _otpView, int marginStart) {
    super(marginStart);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    int childCount = otpView.getChildCount();
    View each;
    ViewGroup.MarginLayoutParams eachLayoutParams;
    for (int i = 0; i < childCount; i++) {
      each = otpView.getChildAt(i);
      eachLayoutParams = (ViewGroup.MarginLayoutParams) each.getLayoutParams();
      eachLayoutParams.setMarginStart(value);
      each.setLayoutParams(eachLayoutParams);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxMarginStart create(final OtpView otpView, final TypedArray typedArray) {
      final @Px int marginStart = typedArray.getDimensionPixelSize(
          R.styleable.OtpView_otp_boxMargin_start,
          otpView.getContext().getResources().getDimensionPixelSize(
              R.dimen.default_boxMargin_start));
      return new StateBoxMarginStart(otpView, marginStart);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

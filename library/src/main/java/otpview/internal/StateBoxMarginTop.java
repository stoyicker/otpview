package otpview.internal;

import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Px;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxMarginTop extends State<Integer> implements Applicable {
  private final OtpView otpView;

  public StateBoxMarginTop(final OtpView _otpView, int marginTop) {
    super(marginTop);
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
      eachLayoutParams.topMargin = value;
      each.setLayoutParams(eachLayoutParams);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxMarginTop create(final OtpView otpView, final TypedArray typedArray) {
      final @Px int marginTop = typedArray.getDimensionPixelSize(
          R.styleable.OtpView_otp_boxMargin_top,
          otpView.getContext().getResources().getDimensionPixelSize(
              R.dimen.default_boxMargin_top));
      return new StateBoxMarginTop(otpView, marginTop);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

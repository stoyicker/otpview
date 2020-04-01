package otpview.internal;

import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Px;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxMarginBottom extends State<Integer> implements Applicable {
  private final OtpView otpView;

  public StateBoxMarginBottom(final OtpView _otpView, int marginBottom) {
    super(marginBottom);
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
      eachLayoutParams.bottomMargin = value;
      each.setLayoutParams(eachLayoutParams);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxMarginBottom create(final OtpView otpView, final TypedArray typedArray) {
      final @Px int marginBottom = typedArray.getDimensionPixelSize(
          R.styleable.OtpView_otp_boxMargin_bottom,
          otpView.getContext().getResources().getDimensionPixelSize(
              R.dimen.default_boxMargin_bottom));
      return new StateBoxMarginBottom(otpView, marginBottom);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

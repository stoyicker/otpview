package otpview.internal;

import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Px;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxMarginEnd extends State<Integer> implements Applicable {
  private final OtpView otpView;

  public StateBoxMarginEnd(final OtpView _otpView, int marginEnd) {
    super(marginEnd);
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
      eachLayoutParams.setMarginEnd(value);
      each.setLayoutParams(eachLayoutParams);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxMarginEnd create(final OtpView otpView, final TypedArray typedArray) {
      final @Px int marginEnd = typedArray.getDimensionPixelSize(
          R.styleable.OtpView_otp_boxMargin_end,
          otpView.getContext().getResources().getDimensionPixelSize(
              R.dimen.default_boxMargin_end));
      return new StateBoxMarginEnd(otpView, marginEnd);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

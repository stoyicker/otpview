package otpview.internal;

import android.content.res.TypedArray;
import android.view.View;

import androidx.annotation.Px;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxPaddingTop extends State<Integer> implements Applicable {
  private final OtpView otpView;

  public StateBoxPaddingTop(final OtpView _otpView, int paddingTop) {
    super(paddingTop);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    int childCount = otpView.getChildCount();
    View each;
    for (int i = 0; i < childCount; i++) {
      each = otpView.getChildAt(i);
      each.setPaddingRelative(
          each.getPaddingStart(), value, each.getPaddingEnd(), each.getPaddingBottom());
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxPaddingTop create(final OtpView otpView, final TypedArray typedArray) {
      final @Px int paddingTop = typedArray.getDimensionPixelSize(
          R.styleable.OtpView_otp_boxMargin_top,
          otpView.getContext().getResources().getDimensionPixelSize(
              R.dimen.default_boxPadding_top));
      return new StateBoxPaddingTop(otpView, paddingTop);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

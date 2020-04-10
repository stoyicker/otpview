package otpview.internal;

import android.content.res.TypedArray;
import android.view.View;

import androidx.annotation.Px;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxPaddingBottom extends State<Integer> implements Applicable {
  private final OtpView otpView;

  public StateBoxPaddingBottom(final OtpView _otpView, int paddingBottom) {
    super(paddingBottom);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    int childCount = otpView.getChildCount();
    View each;
    for (int i = 0; i < childCount; i++) {
      each = otpView.getChildAt(i);
      each.setPaddingRelative(
          each.getPaddingStart(), each.getPaddingTop(), each.getPaddingEnd(), value);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxPaddingBottom create(final OtpView otpView, final TypedArray typedArray) {
      final @Px int paddingBottom = typedArray.getDimensionPixelSize(
          R.styleable.OtpView_otp_boxPadding_bottom,
          otpView.getContext().getResources().getDimensionPixelSize(
              R.dimen.default_boxPadding_bottom));
      return new StateBoxPaddingBottom(otpView, paddingBottom);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

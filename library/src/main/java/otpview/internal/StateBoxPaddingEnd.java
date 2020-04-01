package otpview.internal;

import android.content.res.TypedArray;
import android.view.View;

import androidx.annotation.Px;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxPaddingEnd extends State<Integer> implements Applicable {
  private final OtpView otpView;

  public StateBoxPaddingEnd(final OtpView _otpView, int paddingEnd) {
    super(paddingEnd);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    int childCount = otpView.getChildCount();
    View each;
    for (int i = 0; i < childCount; i++) {
      each = otpView.getChildAt(i);
      each.setPaddingRelative(
          each.getPaddingStart(), each.getPaddingTop(), value, each.getPaddingBottom());
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxPaddingEnd create(final OtpView otpView, final TypedArray typedArray) {
      final @Px int paddingEnd = typedArray.getDimensionPixelSize(
          R.styleable.OtpView_otp_boxPadding_end,
          otpView.getContext().getResources().getDimensionPixelSize(
              R.dimen.default_boxPadding_end));
      return new StateBoxPaddingEnd(otpView, paddingEnd);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

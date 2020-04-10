package otpview.internal;

import android.content.res.TypedArray;
import android.view.View;

import androidx.annotation.Px;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxPaddingStart extends State<Integer> implements Applicable {
  private final OtpView otpView;

  public StateBoxPaddingStart(final OtpView _otpView, int paddingStart) {
    super(paddingStart);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    int childCount = otpView.getChildCount();
    View each;
    for (int i = 0; i < childCount; i++) {
      each = otpView.getChildAt(i);
      each.setPaddingRelative(
          value, each.getPaddingTop(), each.getPaddingEnd(), each.getPaddingBottom());
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxPaddingStart create(final OtpView otpView, final TypedArray typedArray) {
      final @Px int paddingStart = typedArray.getDimensionPixelSize(
          R.styleable.OtpView_otp_boxPadding_start,
          otpView.getContext().getResources().getDimensionPixelSize(
              R.dimen.default_boxPadding_start));
      return new StateBoxPaddingStart(otpView, paddingStart);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

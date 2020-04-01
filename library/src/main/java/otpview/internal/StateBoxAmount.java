package otpview.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateBoxAmount extends State<Integer> implements Applicable {
  private final OtpView otpView;
  private final BoxEditText.Factory boxEditTextFactory = BoxEditText.Factory.Loader.INSTANCE;
  private int lastCalculatedOrientation;

  public StateBoxAmount(
      final OtpView _otpView, int boxAmount) {
    super(boxAmount);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    final int currentOrientation = otpView.getOrientation();
    if (lastCalculatedOrientation != currentOrientation) {
      otpView.removeAllViews();
      lastCalculatedOrientation = currentOrientation;
    }
    final int initialChildCount = otpView.getChildCount();
    final int diff = value - initialChildCount;
    otpView.clearAnimation();
    if (diff > 0) {
      final LinearLayout.LayoutParams[] layoutParams = new LinearLayout.LayoutParams[diff];
      if (otpView.getOrientation() == LinearLayout.HORIZONTAL) {
        for (int i = 0; i < diff; i++) {
          layoutParams[i] = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        }
      } else {
        for (int i = 0; i < diff; i++) {
          layoutParams[i] = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        }
      }
      final Context context = otpView.getContext();
      for (int i = 0; i < diff; i++) {
        layoutParams[i].weight = 1;
        otpView.addView(boxEditTextFactory.create(
            context, otpView.getChildCount()), layoutParams[i]);
      }
    } else if (diff < 0) {
      otpView.clearDisappearingChildren();
      otpView.removeViews(initialChildCount + diff, -diff);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxAmount create(final OtpView otpView, final TypedArray typedArray) {
      final int boxAmount = typedArray.getInt(
          R.styleable.OtpView_otp_boxAmount,
          otpView.getContext().getResources().getInteger(R.integer.default_boxAmount));
      return new StateBoxAmount(otpView, boxAmount);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

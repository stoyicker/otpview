package otpview.internal;

import android.content.res.TypedArray;
import android.widget.TextView;

import org.stoyicker.otpview.R;

import otpview.BoxInputType;
import otpview.OtpView;

public final class StateBoxInputType extends State<Integer> implements Applicable {
  private final OtpView otpView;

  public StateBoxInputType(final OtpView _otpView, @BoxInputType int boxInputType) {
    super(boxInputType);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    int childCount = otpView.getChildCount();
    for (int i = 0; i < childCount; i++) {
      ((TextView) otpView.getChildAt(i)).setInputType(value);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxInputType create(final OtpView otpView, final TypedArray typedArray) {
      final @BoxInputType int boxInputType = typedArray.getInt(
          R.styleable.OtpView_otp_boxInputType,
          otpView.getContext().getResources().getInteger(R.integer.default_boxInputType));
      return new StateBoxInputType(otpView, boxInputType);
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

package otpview.internal;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import org.stoyicker.otpview.R;

import otpview.OtpView;
import otpview.internal.android.graphics.drawable.LayerDrawableFactory;

public final class StateBoxMask extends State<Drawable> implements Applicable {
  private static final LayerDrawableFactory LAYER_DRAWABLE_FACTORY =
      LayerDrawableFactory.Loader.INSTANCE;
  private final OtpView otpView;

  public StateBoxMask(final OtpView _otpView, final Drawable maskDrawable) {
    super(maskDrawable);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    if (value == null || !otpView.isMasked()) {
      return;
    }
    final Drawable boxBackground = otpView.getBoxBackground();
    final Drawable.ConstantState rawBackgroundConstantState;
    if (boxBackground == null) {
      rawBackgroundConstantState = null;
    } else {
      rawBackgroundConstantState = boxBackground.getConstantState();
    }
    final Drawable.ConstantState newBackgroundConstantState = LAYER_DRAWABLE_FACTORY.create(
        otpView.getBoxBackground(), value).getConstantState();
    if (newBackgroundConstantState == null) {
      // No idea when or why this may happen
      return;
    }
    TextView each;
    for (int i = 0; i < otpView.getChildCount(); i++) {
      each = (TextView) otpView.getChildAt(i);
      if (each.getText().length() > 0) {
        each.setBackground(newBackgroundConstantState.newDrawable());
      } else {
        each.setBackground(
            rawBackgroundConstantState == null ? null : rawBackgroundConstantState.newDrawable());
      }
    }
  }

  public static final class FactoryFromTypedArray {
    public StateBoxMask create(final OtpView otpView, final TypedArray typedArray) {
      return new StateBoxMask(otpView, typedArray.getDrawable(R.styleable.OtpView_otp_boxMask));
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }
}

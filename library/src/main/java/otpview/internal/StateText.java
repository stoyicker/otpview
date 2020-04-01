package otpview.internal;

import android.content.res.TypedArray;
import android.util.SparseArray;
import android.widget.TextView;

import org.stoyicker.otpview.R;

import otpview.OtpView;

public final class StateText extends State<SparseArray<Character>> implements Applicable {
  private final OtpView otpView;

  public StateText(final OtpView _otpView, final SparseArray<Character> sparseArray) {
    super(sparseArray);
    otpView = _otpView;
  }

  @Override
  public void apply() {
    final int childCount = otpView.getChildCount();
    Character each;
    int i = 0;
    for (i = 0; i < childCount; i++) {
      each = value.get(i);
      ((TextView) otpView.getChildAt(i)).setText(each == null ? "" : String.valueOf(each));
    }
    // Delete potentially overflowing chars (caused by, for example, removing filled-in boxes)
    final int textSize = value.size();
    for (; i < textSize; i++) {
      value.delete(i);
    }
  }

  public static final class FactoryFromTypedArray {
    public StateText create(final OtpView otpView, final TypedArray typedArray) {
      return FactoryFromCharSequence.Loader.INSTANCE.create(
          otpView, typedArray.getString(R.styleable.OtpView_otp_text));
    }

    public static final class Loader {
      public static final FactoryFromTypedArray INSTANCE = new FactoryFromTypedArray();
    }
  }

  public static final class FactoryFromCharSequence {
    public StateText create(final OtpView otpView, final CharSequence charSequence) {
      final SparseArray<Character> sparseArray = new SparseArray<>();
      final int charSequenceLength = charSequence == null ? 0 : charSequence.length();
      for (int i = 0; i < charSequenceLength; i++) {
        sparseArray.append(i, charSequence.charAt(i));
      }
      return new StateText(otpView, sparseArray);
    }

    public static final class Loader {
      public static final FactoryFromCharSequence INSTANCE = new FactoryFromCharSequence();
    }
  }
}

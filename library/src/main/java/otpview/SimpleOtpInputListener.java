package otpview;

import android.util.SparseArray;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * An {@link OtpInputListener} that invokes its callback only when all boxes are filled in and the
 * content does not match that of its last invocation.
 */
@Keep
public abstract class SimpleOtpInputListener implements OtpInputListener {
  private CharSequence lastInput = null;

  private static String sparseArrayToCharSequence(
      final SparseArray<Character> sparseArray, int boxAmount) {
    final StringBuilder ret = new StringBuilder();
    Character each;
    for (int i = 0; i < boxAmount; i++) {
      each = sparseArray.get(i);
      if (each != null) {
        ret.append(each);
      }
    }
    return ret.toString();
  }

  @Override
  public final void onInput(final @NonNull SparseArray<Character> textRaw, int boxAmount) {
    final String fullInput = sparseArrayToCharSequence(textRaw, boxAmount);
    if (textRaw.size() == boxAmount && lastInput != fullInput) {
      lastInput = fullInput;
      onFullInput(fullInput);
    }
  }

  @SuppressWarnings("WeakerAccess") // Won't work with Kotlin
  public abstract void onFullInput(@NonNull String charSequence);
}

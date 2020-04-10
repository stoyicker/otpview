package otpview;

import android.text.InputType;

import androidx.annotation.IntDef;
import androidx.annotation.Keep;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef(value = {
    BoxInputType.ALPHANUMERIC,
    BoxInputType.ALPHANUMERIC_ALL_CAPS,
    BoxInputType.NUMERIC})
@Keep
public @interface BoxInputType {
  int ALPHANUMERIC = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
  int ALPHANUMERIC_ALL_CAPS = InputType.TYPE_CLASS_TEXT |
      InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS |
      InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
  int NUMERIC = InputType.TYPE_CLASS_NUMBER;
}

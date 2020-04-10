package otpview.internal;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.autofill.HintConstants;

import static android.view.View.IMPORTANT_FOR_AUTOFILL_YES;

@RequiresApi(api = Build.VERSION_CODES.O)
public final class AutoFillConstants {
  public static final int IMPORTANT_FOR_AUTOFILL = IMPORTANT_FOR_AUTOFILL_YES;
  public static final String[] AUTOFILL_HINTS = new String[]{HintConstants.AUTOFILL_HINT_SMS_OTP};
}

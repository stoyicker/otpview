package otpview.internal;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class HideSoftKeyboardRunnable implements Runnable {
  private final View view;

  public HideSoftKeyboardRunnable(final View _view) {
    view = _view;
  }

  @Override
  public void run() {
    final InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
        .getSystemService(Activity.INPUT_METHOD_SERVICE);
    if (inputMethodManager != null) {
      inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }
}

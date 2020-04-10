package otpview.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import otpview.OtpView;

public final class BoxEditText extends EditText implements TextWatcher {
  private final int indexInParent;

  /**
   * For layout editor.
   */
  public BoxEditText(final @NonNull Context context) {
    super(context);
    indexInParent = -1;
  }


  BoxEditText(final Context context, int _indexInParent) {
    super(context);
    indexInParent = _indexInParent;
    init();
  }

  @Override
  public void beforeTextChanged(final CharSequence s, int start, int count, int after) {
  }

  @Override
  public void afterTextChanged(final Editable s) {
    if (s.length() > 1) {
      setText(String.valueOf(s.charAt(0)));
    }
    ((OtpView) getParent()).onCurrentBoxAddition(
        indexInParent,
        s.length() > 0 ? s.charAt(0) : null,
        s.length() > 1 ? s.subSequence(1, s.length()) : "");
  }

  @Override
  public void onTextChanged(final CharSequence s, int start, int before, int count) {
  }

  @Override
  protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
    ((OtpView) getParent()).onChildFocusChanged(focused);
    super.onFocusChanged(focused, direction, previouslyFocusedRect);
    if (focused) {
      if (getText().length() > 0) {
        setText("");
        requestFocus();
      }
    }
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_DEL) {
      ((OtpView) getParent()).onPreviousBoxDeletion(indexInParent);
      return true;
    } else if (keyCode == KeyEvent.KEYCODE_FORWARD_DEL) {
      ((OtpView) getParent()).onCurrentBoxAddition(indexInParent, null, "");
      return true;
    }
    return super.onKeyUp(keyCode, event);
  }

  @Override
  public void onEditorAction(int actionCode) {
    switch (actionCode) {
      case EditorInfo.IME_ACTION_DONE:
      case EditorInfo.IME_ACTION_GO:
      case EditorInfo.IME_ACTION_NEXT:
        ((OtpView) getParent()).onCurrentBoxAddition(indexInParent, null, "");
        break;
      case EditorInfo.IME_ACTION_PREVIOUS:
        ((OtpView) getParent()).onPreviousBoxDeletion(indexInParent);
        break;
      default:
        super.onEditorAction(actionCode);
    }
  }

  @Override
  public boolean dispatchTouchEvent(final MotionEvent event) {
    final int action = event.getAction();
    if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) {
      requestFocus();
    }
    return super.dispatchTouchEvent(event);
  }

  @Override
  public void setTextColor(final ColorStateList colors) {
    if (colors != null) {
      super.setTextColor(colors);
    } else {
      super.setTextColor(getResources().getColor(android.R.color.transparent));
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public int getImportantForAutofill() {
    return AutoFillConstants.IMPORTANT_FOR_AUTOFILL;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  public String[] getAutofillHints() {
    return AutoFillConstants.AUTOFILL_HINTS;
  }

  private void init() {
    setCursorVisible(false);
    setGravity(Gravity.CENTER);
    addTextChangedListener(this);
    setSelectAllOnFocus(true);
    setPaintFlags(0);
    setImeOptions(getImeOptions() | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
  }

  public static final class Factory {
    public BoxEditText create(final Context context, final int indexInParent) {
      return new BoxEditText(context, indexInParent);
    }

    public static final class Loader {
      public static final Factory INSTANCE = new Factory();
    }
  }
}

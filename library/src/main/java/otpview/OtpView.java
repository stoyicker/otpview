package otpview;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;

import org.stoyicker.otpview.R;

import java.util.HashSet;
import java.util.Set;

import otpview.internal.AutoFillConstants;
import otpview.internal.HideSoftKeyboardRunnable;
import otpview.internal.OtpViewSavedState;
import otpview.internal.StateBoxAmount;
import otpview.internal.StateBoxBackground;
import otpview.internal.StateBoxInputType;
import otpview.internal.StateBoxMarginBottom;
import otpview.internal.StateBoxMarginEnd;
import otpview.internal.StateBoxMarginStart;
import otpview.internal.StateBoxMarginTop;
import otpview.internal.StateBoxMask;
import otpview.internal.StateBoxPaddingBottom;
import otpview.internal.StateBoxPaddingEnd;
import otpview.internal.StateBoxPaddingStart;
import otpview.internal.StateBoxPaddingTop;
import otpview.internal.StateBoxTextColor;
import otpview.internal.StateBoxTextSize;
import otpview.internal.StateMasked;
import otpview.internal.StateText;

public final class OtpView extends LinearLayout {
  private StateBoxTextColor stateBoxTextColor;
  private StateBoxTextSize stateBoxTextSize;
  private StateBoxInputType stateBoxInputType;
  private StateBoxAmount stateBoxAmount;
  private StateBoxMarginStart stateBoxMarginStart;
  private StateBoxMarginTop stateBoxMarginTop;
  private StateBoxMarginEnd stateBoxMarginEnd;
  private StateBoxMarginBottom stateBoxMarginBottom;
  private StateBoxPaddingStart stateBoxPaddingStart;
  private StateBoxPaddingTop stateBoxPaddingTop;
  private StateBoxPaddingEnd stateBoxPaddingEnd;
  private StateBoxPaddingBottom stateBoxPaddingBottom;
  private StateBoxBackground stateBoxBackground;
  private StateText stateText;
  private StateMasked stateMasked;
  private StateBoxMask stateBoxMask;
  private Set<OtpInputListener> listenerSet;
  private boolean isRestoringInstanceState = false;
  @SuppressWarnings("ConstantConditions")
  // If myLooper returns null, the constructor is being run off the main thread, which is not
  // supported for android.widget.View inheritors
  private final Handler handler = new Handler(Looper.myLooper());
  private final Runnable hideSoftKeyboardRunnable = new HideSoftKeyboardRunnable(this);

  @Keep
  public OtpView(final @NonNull Context context) {
    super(context);
    init(null);
  }

  @Keep
  public OtpView(final @NonNull Context context, final @Nullable AttributeSet attributeSet) {
    super(context, attributeSet);
    init(attributeSet);
  }

  /**
   * Gets the amount of boxes currently in the view.
   *
   * @return The amount of boxes currently in the view.
   * @see R.attr#otp_boxAmount
   */
  @Keep
  public int getBoxAmount() {
    return stateBoxAmount.getValue();
  }

  /**
   * Sets the amount of boxes for the view. Boxes will be added at the end or removed from the end
   * of the view. Note that the current text of the view will be affected by the amount of boxes
   * changing, either because it is trimmed if boxes are removed, or because it gets appended spaces
   * if boxes are added.
   *
   * @param newBoxAmount The new amount of boxes that the view should have (minimum 1)
   * @see R.attr#otp_boxAmount
   */
  @Keep
  public void setBoxAmount(@IntRange(from = 1) int newBoxAmount) {
    if (newBoxAmount < 1) {
      throw new IllegalArgumentException("Illegal box amount " + newBoxAmount);
    }
    stateBoxAmount = new StateBoxAmount(this, newBoxAmount);
    applyAll();
  }

  /**
   * Sets the margin for all sides of the boxes in the view.
   *
   * @param newMargin The new margin for all sides of the boxes in the view, in pixels
   * @see R.attr#otp_boxPadding_start
   */
  @Keep
  public void setBoxMargin(@Px int newMargin) {
    setBoxMarginStart(newMargin);
    setBoxMarginTop(newMargin);
    setBoxMarginEnd(newMargin);
    setBoxMarginBottom(newMargin);
  }

  /**
   * Gets the start margin of the boxes in the view.
   *
   * @return The start margin of the boxes in the view, in pixels.
   * @see R.attr#otp_boxMargin_start
   */
  @Keep
  @Px
  public int getBoxMarginStart() {
    return stateBoxMarginStart.getValue();
  }

  /**
   * Sets the start margin for the boxes in the view.
   *
   * @param newMarginStart The new start margin for the boxes in the view, in pixels
   * @see R.attr#otp_boxMargin_start
   */
  @Keep
  public void setBoxMarginStart(@Px int newMarginStart) {
    stateBoxMarginStart = new StateBoxMarginStart(this, newMarginStart);
    applyAll();
  }

  /**
   * Gets the top margin of the boxes in the view.
   *
   * @return The top margin of the boxes in the view, in pixels
   * @see R.attr#otp_boxMargin_top
   */
  @Keep
  @Px
  public int getBoxMarginTop() {
    return stateBoxMarginTop.getValue();
  }

  /**
   * Sets the top margin for the boxes in the view.
   *
   * @param newMarginTop The new top margin for the boxes in the view, in pixels
   * @see R.attr#otp_boxMargin_top
   */
  @Keep
  public void setBoxMarginTop(@Px int newMarginTop) {
    stateBoxMarginTop = new StateBoxMarginTop(this, newMarginTop);
    applyAll();
  }

  /**
   * Gets the end margin of the boxes in the view.
   *
   * @return The end margin of the boxes in the view, in pixels
   * @see R.attr#otp_boxMargin_end
   */
  @Keep
  @Px
  public int getBoxMarginEnd() {
    return stateBoxMarginEnd.getValue();
  }

  /**
   * Sets the end margin for the boxes in the view.
   *
   * @param newMarginEnd The new end margin for the boxes in the view, in pixels
   * @see R.attr#otp_boxMargin_end
   */
  @Keep
  public void setBoxMarginEnd(@Px int newMarginEnd) {
    stateBoxMarginEnd = new StateBoxMarginEnd(this, newMarginEnd);
    applyAll();
  }

  /**
   * Gets the bottom margin of the boxes in the view.
   *
   * @return The bottom margin of the boxes in the view, in pixels
   * @see R.attr#otp_boxMargin_bottom
   */
  @Keep
  @Px
  public int getBoxMarginBottom() {
    return stateBoxMarginBottom.getValue();
  }

  /**
   * Sets the bottom margin for the boxes in the view.
   *
   * @param newMarginBottom The new bottom margin for the boxes in the view, in pixels
   * @see R.attr#otp_boxMargin_bottom
   */
  @Keep
  public void setBoxMarginBottom(@Px int newMarginBottom) {
    stateBoxMarginBottom = new StateBoxMarginBottom(this, newMarginBottom);
    applyAll();
  }

  /**
   * Sets the padding for all sides of the boxes in the view.
   *
   * @param newPadding The new padding for all sides of the boxes in the view, in pixels
   * @see R.attr#otp_boxPadding_start
   */
  @Keep
  public void setBoxPadding(@Px int newPadding) {
    setBoxPaddingStart(newPadding);
    setBoxPaddingTop(newPadding);
    setBoxPaddingEnd(newPadding);
    setBoxPaddingBottom(newPadding);
  }

  /**
   * Gets the start padding of the boxes in the view.
   *
   * @return The start padding of the boxes in the view, in pixels
   * @see R.attr#otp_boxPadding_start
   */
  @Keep
  @Px
  public int getBoxPaddingStart() {
    return stateBoxPaddingStart.getValue();
  }

  /**
   * Sets the start padding for the boxes in the view.
   *
   * @param newPaddingStart The new start padding for the boxes in the view, in pixels
   * @see R.attr#otp_boxPadding_start
   */
  @Keep
  public void setBoxPaddingStart(@Px int newPaddingStart) {
    stateBoxPaddingStart = new StateBoxPaddingStart(this, newPaddingStart);
    applyAll();
  }

  /**
   * Gets the top padding of the boxes in the view.
   *
   * @return The top padding of the boxes in the view, in pixels
   * @see R.attr#otp_boxPadding_top
   */
  @Keep
  @Px
  public int getBoxPaddingTop() {
    return stateBoxPaddingTop.getValue();
  }

  /**
   * Sets the top padding for the boxes in the view.
   *
   * @param newPaddingTop The new top padding for the boxes in the view, in pixels
   * @see R.attr#otp_boxPadding_top
   */
  @Keep
  public void setBoxPaddingTop(@Px int newPaddingTop) {
    stateBoxPaddingTop = new StateBoxPaddingTop(this, newPaddingTop);
    applyAll();
  }

  /**
   * Gets the end padding of the boxes in the view.
   *
   * @return The end padding of the boxes in the view, in pixels.
   * @see R.attr#otp_boxPadding_end
   */
  @Keep
  @Px
  public int getBoxPaddingEnd() {
    return stateBoxPaddingEnd.getValue();
  }

  /**
   * Sets the end padding for the boxes in the view.
   *
   * @param newPaddingEnd The new end padding for the boxes in the view, in pixels
   * @see R.attr#otp_boxPadding_end
   */
  @Keep
  public void setBoxPaddingEnd(@Px int newPaddingEnd) {
    stateBoxPaddingEnd = new StateBoxPaddingEnd(this, newPaddingEnd);
    applyAll();
  }

  /**
   * Gets the bottom margin of the boxes in the view.
   *
   * @return The bottom margin of the boxes in the view, in pixels
   * @see R.attr#otp_boxPadding_bottom
   */
  @Keep
  @Px
  public int getBoxPaddingBottom() {
    return stateBoxPaddingBottom.getValue();
  }

  /**
   * Sets the bottom padding for the boxes in the view.
   *
   * @param newPaddingBottom The new bottom padding for the boxes in the view, in pixels
   * @see R.attr#otp_boxMargin_bottom
   */
  @Keep
  public void setBoxPaddingBottom(@Px int newPaddingBottom) {
    stateBoxPaddingBottom = new StateBoxPaddingBottom(this, newPaddingBottom);
    applyAll();
  }

  /**
   * Gets the {@link Drawable} used as background for the boxes.
   *
   * @return The {@link Drawable} used as background for the boxes
   * @see R.attr#otp_boxBackground
   */
  @Keep
  @Nullable
  public Drawable getBoxBackground() {
    return stateBoxBackground.getValue();
  }

  /**
   * Sets the color to be used as a background for the boxes.
   *
   * @param newColorRes A resource pointing to the new color to be as a background for the boxes
   * @see R.attr#otp_boxBackground
   */
  @Keep
  public void setBoxBackgroundColorResource(@ColorRes int newColorRes) {
    setBoxBackground(new ColorDrawable(getContext().getResources().getColor(newColorRes)));
  }

  /**
   * Sets the drawable to be used as a background for the boxes.
   *
   * @param newDrawableRes A resource pointing to the new {@link Drawable} to be as a background for
   *                       the boxes
   * @see R.attr#otp_boxBackground
   */
  @Keep
  public void setBoxBackgroundDrawableResource(@DrawableRes int newDrawableRes) {
    setBoxBackground(getContext().getResources().getDrawable(newDrawableRes));
  }

  /**
   * Sets the color to be used as a background for the boxes.
   *
   * @param newColor The new color to be as a background for the boxes
   * @see R.attr#otp_boxBackground
   */
  @Keep
  public void setBoxBackground(@ColorInt int newColor) {
    setBoxBackground(new ColorDrawable(newColor));
  }

  /**
   * Sets the {@link Drawable} to be used as a background for the boxes.
   *
   * @param newDrawable The new {@link Drawable} to be used as a background for the boxes
   * @see R.attr#otp_boxBackground
   */
  @Keep
  public void setBoxBackground(final @Nullable Drawable newDrawable) {
    stateBoxBackground = new StateBoxBackground(this, newDrawable);
    applyAll();
  }

  /**
   * Gets the color for the text in the boxes.
   *
   * @return A {@link ColorStateList} describing the color(s) for the text in the boxes
   * @see R.attr#otp_boxTextColor
   */
  @Keep
  @SuppressLint("KotlinPropertyAccess") // There are different setters, but only one getter
  @NonNull
  public ColorStateList getBoxTextColor() {
    return stateBoxTextColor.getValue();
  }

  /**
   * Sets the color for the text in the boxes
   *
   * @param newColorRes The resource for new color to use for the boxes where the code
   *                    characters are shown. Use <code>state_focused</code> to apply
   *                    different customization to a box when it is active
   * @see R.attr#otp_boxTextColor
   */
  @Keep
  public void setBoxTextColorResource(@ColorRes int newColorRes) {
    setBoxTextColorStateList(
        ColorStateList.valueOf(getContext().getResources().getColor(newColorRes)));
  }

  /**
   * Sets the color for the text in the boxes.
   *
   * @param newColorRes The resource for new {@link ColorStateList} to use for the boxes where the
   *                    code characters are shown. Use <code>state_focused</code> to apply
   *                    different customization to a box when it is active
   * @see R.attr#otp_boxTextColor
   */
  @Keep
  public void setBoxTextColorStateListResource(@ColorRes int newColorRes) {
    setBoxTextColorStateList(getContext().getResources().getColorStateList(newColorRes));
  }

  /**
   * Sets the color for the text in the boxes.
   *
   * @param newColor The new color to use for the boxes where the code
   *                 characters are shown. Use <code>state_focused</code> to apply
   *                 different customization to a box when it is active
   * @see R.attr#otp_boxTextColor
   */
  @Keep
  public void setBoxTextColor(@ColorInt int newColor) {
    setBoxTextColorStateList(ColorStateList.valueOf(newColor));
  }

  /**
   * Sets the color for the text in the boxes.
   *
   * @param newColorStateList The new {@link ColorStateList} to use for the boxes where the code
   *                          characters are shown. Use <code>state_focused</code> to apply
   *                          different customization to a box when it is active
   * @see R.attr#otp_boxTextColor
   */
  @Keep
  public void setBoxTextColorStateList(final @NonNull ColorStateList newColorStateList) {
    stateBoxTextColor = new StateBoxTextColor(this, newColorStateList);
    applyAll();
  }

  /**
   * Gets the size (in raw pixels) of the text in the boxes of this view.
   *
   * @return The size (in raw pixels) of the text in the boxes of this view
   * @see R.attr#otp_boxTextSize
   */
  @Keep
  @Px
  public float getBoxTextSize() {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        getBoxTextScaledSize(), getContext().getResources().getDisplayMetrics());
  }

  /**
   * Sets the size (in raw pixels) of the text in the boxes of this view.
   *
   * @param newTextSize The size (in raw pixels) of the text in the boxes of this view
   * @see R.attr#otp_boxTextSize
   */
  @Keep
  public void setBoxTextSize(@Px float newTextSize) {
    setBoxTextScaledSize(
        newTextSize / getContext().getResources().getDisplayMetrics().scaledDensity);
  }

  /**
   * Gets the size (in scaled pixels) of the text in the boxes of this view.
   *
   * @return The size (in scaled pixels) of the text in the boxes of this view
   * @see R.attr#otp_boxTextSize
   */
  @Keep
  @Px
  public float getBoxTextScaledSize() {
    return stateBoxTextSize.getValue();
  }

  /**
   * Sets the size (in scaled pixels) of the text in the boxes of this view.
   *
   * @param newTextSize The size (in scaled pixels) of the text in the boxes of this view
   * @see R.attr#otp_boxTextSize
   */
  @Keep
  public void setBoxTextScaledSize(@Px float newTextSize) {
    stateBoxTextSize = new StateBoxTextSize(this, newTextSize);
    applyAll();
  }

  /**
   * Gets the current input type of the view.
   *
   * @return The current input type of the view, as specified by {@link BoxInputType}
   * @see R.attr#otp_boxInputType
   */
  @Keep
  @BoxInputType
  public int getBoxInputType() {
    return stateBoxInputType.getValue();
  }

  /**
   * Sets the input type of the view. This will condition the virtual input method shown on screen
   * when the user interacts with the boxes.
   *
   * @param newBoxInputType The desired new input type, any of {@link BoxInputType}
   * @see R.attr#otp_boxInputType
   */
  @Keep
  public void setBoxInputType(@BoxInputType int newBoxInputType) {
    stateBoxInputType = new StateBoxInputType(this, newBoxInputType);
    applyAll();
  }

  /**
   * Gets the current text in the view as a representation built by putting together all characters
   * in the view. Empty boxes are skipped, so the length of what you get may not match the amount
   * of boxes in the view
   *
   * @return A representation of the current text in the view
   * @see R.attr#otp_text
   * @see #getTextRaw()
   */
  @Keep
  @NonNull
  public String getText() {
    final SparseArray<Character> text = stateText.getValue();
    final int childCount = getChildCount();
    final StringBuilder ret = new StringBuilder();
    Character each;
    for (int i = 0; i < childCount; i++) {
      each = text.get(i);
      if (each != null) {
        ret.append(each);
      }
    }
    return ret.toString();
  }

  /**
   * Sets the text in the view. If the view is set to be masked, the text will not be revealed at
   * all. The content is distributed one character per box, starting with the first box, this being
   * the left-most one in LTR locales and the right-most one in RTL locales.
   *
   * @param newTextRes The resource for the text to set in this view
   * @see R.attr#otp_text
   */
  @Keep
  public void setTextResource(final @StringRes int newTextRes) {
    setText(getContext().getString(newTextRes));
  }

  /**
   * Sets the text in the view. If the view is set to be masked, the text will not be revealed at
   * all. The content is distributed one character per box, starting with the first box, this being
   * the left-most one in LTR locales and the right-most one in RTL locales.
   *
   * @param newText The text to set in this view
   * @see R.attr#otp_text
   */
  @Keep
  public void setText(final @NonNull CharSequence newText) {
    stateText = StateText.FactoryFromCharSequence.Loader.INSTANCE.create(this, newText);
    applyAll();
  }

  /**
   * Gets the characters currently in the view mapped to the position they occupy, 0-indexed.
   *
   * @return A {@link SparseArray} holding the {@link Character}s currently in the view mapped to
   * the position they occupy, 0-indexed. If a box is empty, the corresponding index will be missing
   * from the object
   * @see R.attr#otp_text
   */
  @Keep
  @NonNull
  public SparseArray<Character> getTextRaw() {
    return stateText.getValue();
  }

  /**
   * Sets the characters currently in the boxes.
   *
   * @param newTextRaw A {@link SparseArray} of {@link Character} mapping the contents for each box
   *                   to the index of that box (0-indexed) in the view
   * @see R.attr#otp_text
   */
  @Keep
  public void setTextRaw(final @NonNull SparseArray<Character> newTextRaw) {
    stateText = new StateText(this, newTextRaw);
    applyAll();
  }

  /**
   * Gets the masked state of the view.
   *
   * @return <value>true</value> if the view is masked; <value>false</value> otherwise
   */
  @Keep
  public boolean isMasked() {
    return stateMasked.getValue();
  }

  /**
   * Sets the masked state of the view.
   *
   * @param newMasked <value>true</value> to mask the content in the view. <value>false</value> to
   *                  un-mask it
   */
  @Keep
  public void setMasked(boolean newMasked) {
    stateMasked = new StateMasked(this, newMasked);
    applyAll();
  }

  /**
   * Gets the {@link Drawable} used as mask for the boxes.
   *
   * @return The {@link Drawable} used as mask for the boxes
   * @see R.attr#otp_boxMask
   */
  @Keep
  @Nullable
  public Drawable getBoxMask() {
    return stateBoxBackground.getValue();
  }

  /**
   * Sets the color to be used as a mask for the boxes.
   *
   * @param newColorRes A resource pointing to the new color to be as a mask for the boxes
   * @see R.attr#otp_boxMask
   */
  @Keep
  public void setBoxMaskColorResource(@ColorRes int newColorRes) {
    setBoxMask(new ColorDrawable(getContext().getResources().getColor(newColorRes)));
  }

  /**
   * Sets the drawable to be used as a mask for the boxes.
   *
   * @param newDrawableRes A resource pointing to the new {@link Drawable} to be as a mask for the
   *                       boxes
   * @see R.attr#otp_boxMask
   */
  @Keep
  public void setBoxMaskDrawableResource(@DrawableRes int newDrawableRes) {
    setBoxMask(getContext().getResources().getDrawable(newDrawableRes));
  }

  /**
   * Sets the color to be used as a mask for the boxes.
   *
   * @param newColor The new color to be as a mask for the boxes
   * @see R.attr#otp_boxMask
   */
  @Keep
  public void setBoxMask(@ColorInt int newColor) {
    setBoxMask(new ColorDrawable(newColor));
  }

  /**
   * Sets the {@link Drawable} to be used as a mask for the boxes.
   *
   * @param newDrawable The new {@link Drawable} to be used as a mask for the boxes
   * @see R.attr#otp_boxMask
   */
  @Keep
  public void setBoxMask(final @Nullable Drawable newDrawable) {
    stateBoxMask = new StateBoxMask(this, newDrawable);
    applyAll();
  }

  @Override
  public void setOrientation(int orientation) {
    super.setOrientation(orientation);
    applyAll();
  }

  /**
   * Adds an {@link OtpInputListener} to listen for input changes if it isn't already registered.
   * For most cases, it is recommended that you use an instance of {@link SimpleOtpInputListener}
   *
   * @param listener The {@link OtpInputListener} to register
   * @see #unregisterOtpInputListener(OtpInputListener)
   * @see SimpleOtpInputListener
   */
  @Keep
  public void registerOtpInputListener(final @NonNull OtpInputListener listener) {
    if (listenerSet == null) {
      listenerSet = new HashSet<>(1);
    }
    listenerSet.add(listener);
  }

  /**
   * Removes a previously registered {@link OtpInputListener}, if it is indeed registered.
   *
   * @param listener The {@link OtpInputListener} to unregister
   * @return <code>true</code> if the listener was found among the registered ones (and, therefore,
   * unregistered). <code>false</code> otherwise
   * @see #registerOtpInputListener(OtpInputListener)
   */
  @Keep
  public boolean unregisterOtpInputListener(final @NonNull OtpInputListener listener) {
    if (listenerSet != null) {
      return listenerSet.remove(listener);
    }
    return false;
  }

  /**
   * @see #requestFocusFirstBlankBox()
   */
  @Override
  public boolean requestFocus(int direction, final @Nullable Rect previouslyFocusedRect) {
    final boolean isLtr = getLayoutDirection() == LAYOUT_DIRECTION_LTR;
    final int childCount = getChildCount();
    int focusedIndex = -1;
    for (int i = 0; i < childCount; i++) {
      if (getChildAt(i).hasFocus()) {
        focusedIndex = i;
        break;
      }
    }
    switch (direction) {
      case FOCUS_UP:
      case FOCUS_DOWN:
        return super.requestFocus(direction, previouslyFocusedRect);
      case FOCUS_LEFT:
        return requestFocus(isLtr ? FOCUS_BACKWARD : FOCUS_FORWARD, null);
      case FOCUS_RIGHT:
        return requestFocus(isLtr ? FOCUS_FORWARD : FOCUS_BACKWARD, null);
      case FOCUS_BACKWARD:
        if (focusedIndex == 0) {
          return super.requestFocus(direction, previouslyFocusedRect);
        }
        return getChildAt(focusedIndex - 1).requestFocus();
      case FOCUS_FORWARD:
        if (focusedIndex == childCount - 1) {
          return super.requestFocus(direction, previouslyFocusedRect);
        }
        return getChildAt(focusedIndex + 1).requestFocus();
      default:
        throw new IllegalArgumentException("Illegal direction " + direction);
    }
  }

  /**
   * Gives focus to the first box without text. Note that when a box gains focus, it (1) becomes
   * active, (2) erases its text, if any, and (3) shows the soft keyboard.
   *
   * @return <code>true</code> if a box (regardless of whether it was focused before or not) has
   * requested focus. <code>false</code> otherwise.
   */
  @Keep
  public boolean requestFocusFirstBlankBox() {
    final View viewWithFocus = getFocusedChild();
    final int childCount = getChildCount();
    TextView each;
    for (int i = 0; i < childCount; i++) {
      each = ((TextView) getChildAt(i));
      if (each.getText().length() == 0) {
        if (each != viewWithFocus) {
          each.requestFocus();
        }
        return true;
      }
    }
    return false;
  }

  @Override
  protected void onRestoreInstanceState(final @NonNull Parcelable state) {
    super.onRestoreInstanceState(state);
    isRestoringInstanceState = true;
    final OtpViewSavedState otpViewSavedState = (OtpViewSavedState) state;
    stateBoxAmount = new StateBoxAmount(this, otpViewSavedState.getBoxAmount());
    stateBoxMarginStart = new StateBoxMarginStart(this, otpViewSavedState.getBoxMarginStart());
    stateBoxMarginTop = new StateBoxMarginTop(this, otpViewSavedState.getBoxMarginTop());
    stateBoxMarginEnd = new StateBoxMarginEnd(this, otpViewSavedState.getBoxMarginEnd());
    stateBoxMarginBottom = new StateBoxMarginBottom(this, otpViewSavedState.getBoxMarginBottom());
    stateBoxPaddingStart = new StateBoxPaddingStart(this, otpViewSavedState.getBoxPaddingStart());
    stateBoxPaddingTop = new StateBoxPaddingTop(this, otpViewSavedState.getBoxPaddingTop());
    stateBoxPaddingEnd = new StateBoxPaddingEnd(this, otpViewSavedState.getBoxPaddingEnd());
    stateBoxPaddingBottom = new StateBoxPaddingBottom(
        this, otpViewSavedState.getBoxPaddingBottom());
    stateBoxTextColor = new StateBoxTextColor(this, otpViewSavedState.getBoxTextColor());
    stateBoxTextSize = new StateBoxTextSize(this, otpViewSavedState.getBoxTextSize());
    stateBoxInputType = new StateBoxInputType(this, otpViewSavedState.getBoxInputType());
    stateText = new StateText(this, otpViewSavedState.getText());
    stateMasked = new StateMasked(this, otpViewSavedState.isMasked());
    super.setOrientation(otpViewSavedState.getOrientation());
    setLayoutDirection(otpViewSavedState.getDirection());
    applyAll();
    isRestoringInstanceState = false;
  }

  @Override
  @NonNull
  protected Parcelable onSaveInstanceState() {
    return new OtpViewSavedState(
        super.onSaveInstanceState(),
        stateBoxAmount.getValue(),
        stateBoxMarginStart.getValue(),
        stateBoxMarginTop.getValue(),
        stateBoxMarginEnd.getValue(),
        stateBoxMarginBottom.getValue(),
        stateBoxPaddingStart.getValue(),
        stateBoxPaddingEnd.getValue(),
        stateBoxPaddingTop.getValue(),
        stateBoxPaddingBottom.getValue(),
        stateBoxTextColor.getValue(),
        stateBoxTextSize.getValue(),
        stateBoxInputType.getValue(),
        stateText.getValue(),
        stateMasked.getValue(),
        getOrientation(),
        getLayoutDirection());
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

  private void init(final AttributeSet attributeSet) {
    setLayoutTransition(new LayoutTransition()); // android:animateLayoutChanges="true"
    setFocusableInTouchMode(true);
    final TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
        attributeSet, R.styleable.OtpView, 0, 0);
    stateBoxTextColor = StateBoxTextColor.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxTextSize = StateBoxTextSize.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxInputType = StateBoxInputType.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxAmount = StateBoxAmount.FactoryFromTypedArray.Loader.INSTANCE.create(this, typedArray);
    stateBoxMarginStart = StateBoxMarginStart.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxMarginTop = StateBoxMarginTop.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxMarginEnd = StateBoxMarginEnd.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxMarginBottom = StateBoxMarginBottom.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxPaddingStart = StateBoxPaddingStart.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxPaddingTop = StateBoxPaddingTop.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxPaddingEnd = StateBoxPaddingEnd.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxPaddingBottom = StateBoxPaddingBottom.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateBoxBackground = StateBoxBackground.FactoryFromTypedArray.Loader.INSTANCE.create(
        this, typedArray);
    stateText = StateText.FactoryFromTypedArray.Loader.INSTANCE.create(this, typedArray);
    stateMasked = StateMasked.FactoryFromTypedArray.Loader.INSTANCE.create(this, typedArray);
    stateBoxMask = StateBoxMask.FactoryFromTypedArray.Loader.INSTANCE.create(this, typedArray);
    typedArray.recycle();
    applyAll();
  }

  private void applyAll() {
    // Keep order, it matters
    stateBoxAmount.apply();
    stateText.apply();
    stateBoxMarginStart.apply();
    stateBoxMarginTop.apply();
    stateBoxMarginEnd.apply();
    stateBoxMarginBottom.apply();
    stateBoxPaddingStart.apply();
    stateBoxPaddingTop.apply();
    stateBoxPaddingEnd.apply();
    stateBoxPaddingBottom.apply();
    stateBoxTextColor.apply();
    stateBoxTextSize.apply();
    stateBoxInputType.apply();
    stateBoxBackground.apply();
    stateMasked.apply();
    stateBoxMask.apply();
  }

  @Override
  protected void onFocusChanged(
      boolean gainFocus, int direction, final @Nullable Rect previouslyFocusedRect) {
    super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    if (gainFocus) {
      final int childCount = getChildCount();
      CharSequence each;
      for (int i = 0; i < childCount; i++) {
        each = ((TextView) getChildAt(i)).getText();
        if (each == null || each.length() == 0) {
          return;
        }
      }
      hideSoftKeyboardRunnable.run();
    }
  }

  @RestrictTo(RestrictTo.Scope.LIBRARY)
  public void onCurrentBoxAddition(
      int srcChildIndex,
      final @Nullable Character addition,
      final @NonNull CharSequence remaining) {
    final int childCount = getChildCount();
    // If srcChildIndex points to the last child or we're done with this box but it remains empty,
    // make it drop focus but do not give it to the next one because (1) it is empty or (2) the next
    // one might have text and we do not want to delete it
    if (addition == null) {
      requestFocus();
    } else {
      final View nextChild = getChildAt(srcChildIndex + 1);
      if (remaining.length() > 0) {
        // If there are chars left, pass them over
        ((TextView) nextChild).setText(remaining);
      } else {
        // Otherwise try to focus the first empty child, if any
        if (!requestFocusFirstBlankBox()) {
          requestFocus();
        }
      }
    }
    if (!isRestoringInstanceState) {
      if (addition != null) {
        stateText.getValue().put(srcChildIndex, addition);
      } else {
        stateText.getValue().delete(srcChildIndex);
      }
    }
    if (listenerSet != null) {
      for (final OtpInputListener each : listenerSet) {
        each.onInput(stateText.getValue(), getChildCount());
      }
    }
    // Do not update the entire UI here, the only modification is the text in the box, which has
    // already been performed
    stateMasked.apply();
  }

  @RestrictTo(RestrictTo.Scope.LIBRARY)
  public void onPreviousBoxDeletion(int srcChildIndex) {
    if (srcChildIndex > 0) {
      getChildAt(srcChildIndex - 1).requestFocus();
    } else {
      requestFocus();
      hideSoftKeyboardRunnable.run();
    }
    applyAll();
  }

  @RestrictTo(RestrictTo.Scope.LIBRARY)
  public void onChildFocusChanged(boolean newFocus) {
    if (newFocus) {
      handler.removeCallbacks(hideSoftKeyboardRunnable);
    } else {
      handler.postAtFrontOfQueue(hideSoftKeyboardRunnable);
    }
  }
}

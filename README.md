# OtpView
### A lightweight, highly-customizable and reliable OTP input field for Android
### Check out the sample app on [the releases page](https://github.com/stoyicker/otpview/releases/latest) or on [Google Play](https://play.google.com/store/apps/details?id=otpview.demo)!
[![CircleCI](https://circleci.com/gh/stoyicker/otpview.svg?style=svg)](https://circleci.com/gh/stoyicker/otpview)
## Usage
[ ![Download](https://api.bintray.com/packages/stoyicker/otpview/library/images/download.svg) ](https://search.maven.org/search?q=g:com.github.stoyicker.otpview)
```groovy
repositories {
  jcenter()
}
dependencies {
  implementation "com.github.stoyicker.otpview:library:<version>"
}
```
[![Demo video](https://img.youtube.com/vi/zHxbbyeJ2-g/0.jpg)](https://www.youtube.com/watch?v=zHxbbyeJ2-g "OtpView demo video")
|                                              	    |                         	                                                                                        |
|---------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| **Compatible with Kotlin**                   	    | 100%                    	                                                                                        |
| **minSdkVersion**                            	    | 17                      	                                                                                        |
| **onSave/RestoreInstanceState**              	    | <img src="http://getdrawings.com/free-icon/android-checkmark-icon-60.png" alt="drawing" width="16" height="16"/>  |
| **LTR, RTL & top-to-bottom**                 	    | <img src="http://getdrawings.com/free-icon/android-checkmark-icon-60.png" alt="drawing" width="16" height="16"/>  |
| **Fully customizable via state-based drawables** 	| <img src="http://getdrawings.com/free-icon/android-checkmark-icon-60.png" alt="drawing" width="16" height="16"/>  |
| **Customizable attributes via XML**              	| All                     	                                                                                        |
| **Customizable attributes after inflation**    	| All                     	                                                                                        |
## FAQ
### ProGuard/DexGuard/R8
The library includes its own rules. Nothing for you to do.
### Transitive dependencies/apk bloat?
None! The library only depends on a small set of annotations and constants needed to offer proper
support for autocomplete as well as for Kotlin.
### But I use Java, I don't want Kotlin bloat?!
The entire library is written in Java and Kotlin compatibility is achieved via annotations that do
not make it to runtime. So neither Kotlin nor Java users get anything they don't want!
## Full API reference
See [defaults.xml](library/src/main/res/values/defaults.xml) for default values.

### `public int getBoxAmount()`

Gets the amount of boxes currently in the view.

 * **Returns:** The amount of boxes currently in the view.
 * **See also:** R.attr#otp_boxAmount

### `public void setBoxAmount(@IntRange(from = 1) int newBoxAmount)`

Sets the amount of boxes for the view. Boxes will be added at the end or removed from the end of the view. Note that the current text of the view will be affected by the amount of boxes changing, either because it is trimmed if boxes are removed, or because it gets appended spaces if boxes are added.

 * **Parameters:** `newBoxAmount` — The new amount of boxes that the view should have (minimum 1)
 * **See also:** R.attr#otp_boxAmount

### `public void setBoxMargin(@Px int newMargin)`

Sets the margin for all sides of the boxes in the view.

 * **Parameters:** `newMargin` — The new margin for all sides of the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxPadding_start

### `@Px public int getBoxMarginStart()`

Gets the start margin of the boxes in the view.

 * **Returns:** The start margin of the boxes in the view, in pixels.
 * **See also:** R.attr#otp_boxMargin_start

### `public void setBoxMarginStart(@Px int newMarginStart)`

Sets the start margin for the boxes in the view.

 * **Parameters:** `newMarginStart` — The new start margin for the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxMargin_start

### `@Px public int getBoxMarginTop()`

Gets the top margin of the boxes in the view.

 * **Returns:** The top margin of the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxMargin_top

### `public void setBoxMarginTop(@Px int newMarginTop)`

Sets the top margin for the boxes in the view.

 * **Parameters:** `newMarginTop` — The new top margin for the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxMargin_top

### `@Px public int getBoxMarginEnd()`

Gets the end margin of the boxes in the view.

 * **Returns:** The end margin of the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxMargin_end

### `public void setBoxMarginEnd(@Px int newMarginEnd)`

Sets the end margin for the boxes in the view.

 * **Parameters:** `newMarginEnd` — The new end margin for the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxMargin_end

### `@Px public int getBoxMarginBottom()`

Gets the bottom margin of the boxes in the view.

 * **Returns:** The bottom margin of the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxMargin_bottom

### `public void setBoxMarginBottom(@Px int newMarginBottom)`

Sets the bottom margin for the boxes in the view.

 * **Parameters:** `newMarginBottom` — The new bottom margin for the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxMargin_bottom

### `public void setBoxPadding(@Px int newPadding)`

Sets the padding for all sides of the boxes in the view.

 * **Parameters:** `newPadding` — The new padding for all sides of the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxPadding_start

### `@Px public int getBoxPaddingStart()`

Gets the start padding of the boxes in the view.

 * **Returns:** The start padding of the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxPadding_start

### `public void setBoxPaddingStart(@Px int newPaddingStart)`

Sets the start padding for the boxes in the view.

 * **Parameters:** `newPaddingStart` — The new start padding for the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxPadding_start

### `@Px public int getBoxPaddingTop()`

Gets the top padding of the boxes in the view.

 * **Returns:** The top padding of the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxPadding_top

### `public void setBoxPaddingTop(@Px int newPaddingTop)`

Sets the top padding for the boxes in the view.

 * **Parameters:** `newPaddingTop` — The new top padding for the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxPadding_top

### `@Px public int getBoxPaddingEnd()`

Gets the end padding of the boxes in the view.

 * **Returns:** The end padding of the boxes in the view, in pixels.
 * **See also:** R.attr#otp_boxPadding_end

### `public void setBoxPaddingEnd(@Px int newPaddingEnd)`

Sets the end padding for the boxes in the view.

 * **Parameters:** `newPaddingEnd` — The new end padding for the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxPadding_end

### `@Px public int getBoxPaddingBottom()`

Gets the bottom margin of the boxes in the view.

 * **Returns:** The bottom margin of the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxPadding_bottom

### `public void setBoxPaddingBottom(@Px int newPaddingBottom)`

Sets the bottom padding for the boxes in the view.

 * **Parameters:** `newPaddingBottom` — The new bottom padding for the boxes in the view, in pixels
 * **See also:** R.attr#otp_boxMargin_bottom

### `@Nullable public Drawable getBoxBackground()`

Gets the Drawable used as background for the boxes.

 * **Returns:** The Drawable used as background for the boxes
 * **See also:** R.attr#otp_boxBackground

### `public void setBoxBackgroundColorResource(@ColorRes int newColorRes)`

Sets the color to be used as a background for the boxes.

 * **Parameters:** `newColorRes` — A resource pointing to the new color to be as a background for the boxes
 * **See also:** R.attr#otp_boxBackground

### `public void setBoxBackgroundDrawableResource(@DrawableRes int newDrawableRes)`

Sets the drawable to be used as a background for the boxes.

 * **Parameters:** `newDrawableRes` — A resource pointing to the new Drawable to be as a background for

     the boxes
 * **See also:** R.attr#otp_boxBackground

### `public void setBoxBackground(@ColorInt int newColor)`

Sets the color to be used as a background for the boxes.

 * **Parameters:** `newColor` — The new color to be as a background for the boxes
 * **See also:** R.attr#otp_boxBackground

### `public void setBoxBackground(final @Nullable Drawable newDrawable)`

Sets the Drawable to be used as a background for the boxes.

 * **Parameters:** `newDrawable` — The new Drawable to be used as a background for the boxes
 * **See also:** R.attr#otp_boxBackground

### `@SuppressLint("KotlinPropertyAccess")`

Gets the color for the text in the boxes.

 * **Returns:** A ColorStateList describing the color(s) for the text in the boxes
 * **See also:** R.attr#otp_boxTextColor

### `public void setBoxTextColorResource(@ColorRes int newColorRes)`

Sets the color for the text in the boxes

 * **Parameters:** `newColorRes` — The resource for new color to use for the boxes where the code

     characters are shown. Use <code>state_focused</code> to apply

     different customization to a box when it is active
 * **See also:** R.attr#otp_boxTextColor

### `public void setBoxTextColorStateListResource(@ColorRes int newColorRes)`

Sets the color for the text in the boxes.

 * **Parameters:** `newColorRes` — The resource for new ColorStateList to use for the boxes where the

     code characters are shown. Use <code>state_focused</code> to apply

     different customization to a box when it is active
 * **See also:** R.attr#otp_boxTextColor

### `public void setBoxTextColor(@ColorInt int newColor)`

Sets the color for the text in the boxes.

 * **Parameters:** `newColor` — The new color to use for the boxes where the code

     characters are shown. Use <code>state_focused</code> to apply

     different customization to a box when it is active
 * **See also:** R.attr#otp_boxTextColor

### `public void setBoxTextColorStateList(final @NonNull ColorStateList newColorStateList)`

Sets the color for the text in the boxes.

 * **Parameters:** `newColorStateList` — The new ColorStateList to use for the boxes where the code

     characters are shown. Use <code>state_focused</code> to apply

     different customization to a box when it is active
 * **See also:** R.attr#otp_boxTextColor

### `@Px public float getBoxTextSize()`

Gets the size (in raw pixels) of the text in the boxes of this view.

 * **Returns:** The size (in raw pixels) of the text in the boxes of this view
 * **See also:** R.attr#otp_boxTextSize

### `public void setBoxTextSize(@Px float newTextSize)`

Sets the size (in raw pixels) of the text in the boxes of this view.

 * **Parameters:** `newTextSize` — The size (in raw pixels) of the text in the boxes of this view
 * **See also:** R.attr#otp_boxTextSize

### `@Px public float getBoxTextScaledSize()`

Gets the size (in scaled pixels) of the text in the boxes of this view.

 * **Returns:** The size (in scaled pixels) of the text in the boxes of this view
 * **See also:** R.attr#otp_boxTextSize

### `public void setBoxTextScaledSize(@Px float newTextSize)`

Sets the size (in scaled pixels) of the text in the boxes of this view.

 * **Parameters:** `newTextSize` — The size (in scaled pixels) of the text in the boxes of this view
 * **See also:** R.attr#otp_boxTextSize

### `@BoxInputType public int getBoxInputType()`

Gets the current input type of the view.

 * **Returns:** The current input type of the view, as specified by BoxInputType
 * **See also:** R.attr#otp_boxInputType

### `public void setBoxInputType(@BoxInputType int newBoxInputType)`

Sets the input type of the view. This will condition the virtual input method shown on screen when the user interacts with the boxes.

 * **Parameters:** `newBoxInputType` — The desired new input type, any of BoxInputType
 * **See also:** R.attr#otp_boxInputType

### `@NonNull public String getText()`

Gets the current text in the view as a representation built by putting together all characters in the view. Empty boxes are skipped, so the length of what you get may not match the amount of boxes in the view

 * **Returns:** A representation of the current text in the view
 * **See also:**
   * R.attr#otp_text
   * #getTextRaw()

### `public void setTextResource(final @StringRes int newTextRes)`

Sets the text in the view. If the view is set to be masked, the text will not be revealed at all. The content is distributed one character per box, starting with the first box, this being the left-most one in LTR locales and the right-most one in RTL locales.

 * **Parameters:** `newTextRes` — The resource for the text to set in this view
 * **See also:** R.attr#otp_text

### `public void setText(final @NonNull CharSequence newText)`

Sets the text in the view. If the view is set to be masked, the text will not be revealed at all. The content is distributed one character per box, starting with the first box, this being the left-most one in LTR locales and the right-most one in RTL locales.

 * **Parameters:** `newText` — The text to set in this view
 * **See also:** R.attr#otp_text

### `@NonNull public SparseArray<Character> getTextRaw()`

Gets the characters currently in the view mapped to the position they occupy, 0-indexed.

 * **Returns:** A SparseArray holding the Characters currently in the view mapped to

     the position they occupy, 0-indexed. If a box is empty, the corresponding index will be missing

     from the object
 * **See also:** R.attr#otp_text

### `public void setTextRaw(final @NonNull SparseArray<Character> newTextRaw)`

Sets the characters currently in the boxes.

 * **Parameters:** `newTextRaw` — A SparseArray of Character mapping the contents for each box

     to the index of that box (0-indexed) in the view
 * **See also:** R.attr#otp_text

### `public boolean isMasked()`

Gets the masked state of the view.

 * **Returns:** <value>true</value> if the view is masked; <value>false</value> otherwise

### `public void setMasked(boolean newMasked)`

Sets the masked state of the view.

 * **Parameters:** `newMasked` — <value>true</value> to mask the content in the view. <value>false</value> to

     un-mask it

### `@Nullable public Drawable getBoxMask()`

Gets the Drawable used as mask for the boxes.

 * **Returns:** The Drawable used as mask for the boxes
 * **See also:** R.attr#otp_boxMask

### `public void setBoxMaskColorResource(@ColorRes int newColorRes)`

Sets the color to be used as a mask for the boxes.

 * **Parameters:** `newColorRes` — A resource pointing to the new color to be as a mask for the boxes
 * **See also:** R.attr#otp_boxMask

### `public void setBoxMaskDrawableResource(@DrawableRes int newDrawableRes)`

Sets the drawable to be used as a mask for the boxes.

 * **Parameters:** `newDrawableRes` — A resource pointing to the new Drawable to be as a mask for the

     boxes
 * **See also:** R.attr#otp_boxMask

### `public void setBoxMask(@ColorInt int newColor)`

Sets the color to be used as a mask for the boxes.

 * **Parameters:** `newColor` — The new color to be as a mask for the boxes
 * **See also:** R.attr#otp_boxMask

### `public void setBoxMask(final @Nullable Drawable newDrawable)`

Sets the Drawable to be used as a mask for the boxes.

 * **Parameters:** `newDrawable` — The new Drawable to be used as a mask for the boxes
 * **See also:** R.attr#otp_boxMask

### `public void setOrientation(int orientation)`

 * **See also:** 
   * LinearLayout#setOrientation(int)
   * android.R.attr#orientation

### `public void setLayoutDirection(int layoutDirection)`

 * **See also:**
   * View#setLayoutDirection(int)
   * android.R.attr#layoutDirection

### `public void registerOtpInputListener(final @NonNull OtpInputListener listener)`

Adds an OtpInputListener to listen for input changes if it isn't already registered. For most cases, it is recommended that you use an instance of SimpleOtpInputListener

 * **Parameters:** `listener` — The OtpInputListener to register
 * **See also:**
   * #unregisterOtpInputListener(OtpInputListener)
   * SimpleOtpInputListener

### `public boolean unregisterOtpInputListener(final @NonNull OtpInputListener listener)`

Removes a previously registered OtpInputListener, if it is indeed registered.

 * **Parameters:** `listener` — The OtpInputListener to unregister
 * **Returns:** <code>true</code> if the listener was found among the registered ones (and, therefore,

     unregistered). <code>false</code> otherwise
 * **See also:** #registerOtpInputListener(OtpInputListener)

### `public boolean requestFocus(int direction, final @Nullable Rect previouslyFocusedRect)`

 * **See also:** #requestFocusFirstBlankBox()

### `public boolean requestFocusFirstBlankBox()`

Gives focus to the first box without text. Note that when a box gains focus, it (1) becomes active, (2) erases its text, if any, and (3) shows the soft keyboard.

 * **Returns:** <code>true</code> if a box (regardless of whether it was focused before or not) has
     requested focus. <code>false</code> otherwise.

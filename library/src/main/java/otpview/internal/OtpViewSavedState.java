package otpview.internal;

import android.content.res.ColorStateList;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;

import otpview.BoxInputType;

public final class OtpViewSavedState extends View.BaseSavedState {
  public static final Parcelable.Creator<OtpViewSavedState> CREATOR =
      new Parcelable.Creator<OtpViewSavedState>() {
        public OtpViewSavedState createFromParcel(final Parcel in) {
          return new OtpViewSavedState(in);
        }

        public OtpViewSavedState[] newArray(int size) {
          return new OtpViewSavedState[size];
        }
      };
  private final int boxAmount;
  private final int boxMarginStart;
  private final int boxMarginTop;
  private final int boxMarginEnd;
  private final int boxMarginBottom;
  private final int boxPaddingStart;
  private final int boxPaddingTop;
  private final int boxPaddingEnd;
  private final int boxPaddingBottom;
  private final ColorStateList boxTextColor;
  private final float boxTextSize;
  private final @BoxInputType
  int boxInputType;
  private final SparseArray<Character> text;
  private final boolean masked;
  private final int orientation;
  private final int direction;

  public OtpViewSavedState(
      final Parcelable superState,
      int _boxAmount,
      int _boxMarginStart,
      int _boxMarginTop,
      int _boxMarginEnd,
      int _boxMarginBottom,
      int _boxPaddingStart,
      int _boxPaddingTop,
      int _boxPaddingEnd,
      int _boxPaddingBottom,
      final ColorStateList _boxTextColor,
      float _boxTextSize,
      @BoxInputType int _boxInputType,
      final SparseArray<Character> _text,
      final boolean _masked,
      final int _orientation,
      final int _direction) {
    super(superState);
    boxAmount = _boxAmount;
    boxMarginStart = _boxMarginStart;
    boxMarginTop = _boxMarginTop;
    boxMarginEnd = _boxMarginEnd;
    boxMarginBottom = _boxMarginBottom;
    boxPaddingStart = _boxPaddingStart;
    boxPaddingTop = _boxPaddingTop;
    boxPaddingEnd = _boxPaddingEnd;
    boxPaddingBottom = _boxPaddingBottom;
    boxTextColor = _boxTextColor;
    boxTextSize = _boxTextSize;
    boxInputType = _boxInputType;
    text = _text;
    masked = _masked;
    orientation = _orientation;
    direction = _direction;
  }

  @SuppressWarnings("WeakerAccess") // Requires synthetic
  OtpViewSavedState(final Parcel in) {
    super(in);
    boxAmount = in.readInt();
    boxMarginStart = in.readInt();
    boxMarginTop = in.readInt();
    boxMarginEnd = in.readInt();
    boxMarginBottom = in.readInt();
    boxPaddingStart = in.readInt();
    boxPaddingTop = in.readInt();
    boxPaddingEnd = in.readInt();
    boxPaddingBottom = in.readInt();
    boxTextColor = in.readParcelable(getClass().getClassLoader());
    boxTextSize = in.readFloat();
    boxInputType = in.readInt();
    text = in.readSparseArray(getClass().getClassLoader());
    masked = in.readInt() != 0;
    orientation = in.readInt();
    direction = in.readInt();
  }

  @Override
  public void writeToParcel(final Parcel out, int flags) {
    super.writeToParcel(out, flags);
    out.writeInt(boxAmount);
    out.writeInt(boxMarginStart);
    out.writeInt(boxMarginTop);
    out.writeInt(boxMarginEnd);
    out.writeInt(boxMarginBottom);
    out.writeInt(boxPaddingStart);
    out.writeInt(boxPaddingTop);
    out.writeInt(boxPaddingEnd);
    out.writeInt(boxPaddingBottom);
    out.writeParcelable(boxTextColor, 0);
    out.writeFloat(boxTextSize);
    out.writeInt(boxInputType);
    out.writeSparseArray(text);
    out.writeInt(masked ? 1 : 0);
    out.writeInt(orientation);
    out.writeInt(direction);
  }

  @Override
  public int describeContents() {
    return super.describeContents();
  }

  public int getBoxAmount() {
    return boxAmount;
  }

  public int getBoxMarginStart() {
    return boxMarginStart;
  }

  public int getBoxMarginTop() {
    return boxMarginTop;
  }

  public int getBoxMarginEnd() {
    return boxMarginEnd;
  }

  public int getBoxMarginBottom() {
    return boxMarginBottom;
  }

  public int getBoxPaddingStart() {
    return boxPaddingStart;
  }

  public int getBoxPaddingTop() {
    return boxPaddingTop;
  }

  public int getBoxPaddingEnd() {
    return boxPaddingEnd;
  }

  public int getBoxPaddingBottom() {
    return boxPaddingBottom;
  }

  public ColorStateList getBoxTextColor() {
    return boxTextColor;
  }

  public float getBoxTextSize() {
    return boxTextSize;
  }

  public int getBoxInputType() {
    return boxInputType;
  }

  public SparseArray<Character> getText() {
    return text;
  }

  public boolean isMasked() {
    return masked;
  }

  public int getOrientation() {
    return orientation;
  }

  public int getDirection() {
    return direction;
  }
}

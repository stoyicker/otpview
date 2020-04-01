package otpview.internal.android.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

public final class LayerDrawableFactory {
  LayerDrawableFactory() {
  }

  public LayerDrawable create(final Drawable... drawables) {
    return new LayerDrawable(drawables);
  }

  public static final class Loader {
    public static final LayerDrawableFactory INSTANCE = new LayerDrawableFactory();
  }
}

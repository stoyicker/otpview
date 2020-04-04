package otpview.demo

import android.app.Application
import android.graphics.drawable.Drawable

internal class CustomApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    DrawableHolder.apply {
      boxBackgroundActive = this@CustomApplication.resources.getDrawable(
          R.drawable.default_box_background_active)
      boxBackgroundInactive = this@CustomApplication.resources.getDrawable(
          R.drawable.default_box_background_inactive)
      boxMaskActive = this@CustomApplication.resources.getDrawable(
          R.drawable.default_box_mask_active)
      boxMaskInactive = this@CustomApplication.resources.getDrawable(
          R.drawable.default_box_mask_inactive)
    }
  }

  // Really bad idea on a production app. DO NOT COPY THIS PATTERN
  // The reason to do this is that the custom view being demoed does not keep its drawable states
  // (bg and mask) across configuration changes. This is because Drawable cannot be marshalled into
  // a Parcel. In fact, because of this, regular Android views like EditText do not keep Drawables
  // either. However, because this is a demo app, we want users unfamiliar with this behavior to
  // avoid thinking that this might be an issue with our view, so we hold the drawables here instead
  internal object DrawableHolder {
    lateinit var boxBackgroundActive: Drawable
    lateinit var boxBackgroundInactive: Drawable
    lateinit var boxMaskActive: Drawable
    lateinit var boxMaskInactive: Drawable
  }
}

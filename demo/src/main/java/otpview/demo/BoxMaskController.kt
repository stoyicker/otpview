package otpview.demo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.StateListDrawable
import android.util.StateSet
import android.view.View
import otpview.OtpView

internal class BoxMaskController(
    private val otpView: OtpView, activeView: View, inactiveView: View)
  : InactiveActiveDrawableController(activeView = activeView, inactiveView = inactiveView) {
  override fun updateUiImpl(recoveredActive: Drawable, recoveredInactive: Drawable) {
    super.updateUiImpl(extractColor(recoveredActive), extractColor(recoveredInactive))
  }

  private fun extractColor(drawable: Drawable) = when (drawable) {
    is LayerDrawable -> ColorDrawable(drawable.getDrawable(0)!!.run { convertToBitmap(drawable) }
        .getPixel(0, 0))
    is ColorDrawable -> ColorDrawable(drawable.color)
    else -> throw IllegalStateException("Illegal drawable type ${drawable.javaClass.name}")
  }

  private fun convertToBitmap(drawable: Drawable): Bitmap {
    val mutableBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(mutableBitmap)
    drawable.setBounds(0, 0, 1, 1)
    drawable.draw(canvas)
    return mutableBitmap
  }

  override fun onNewValues(active: Drawable, inactive: Drawable) {
    CustomApplication.DrawableHolder.apply {
      boxMaskActive = active
      boxMaskInactive = inactive
    }
    otpView.boxMask = StateListDrawable().apply {
      addState(intArrayOf(android.R.attr.state_focused), active)
      addState(StateSet.WILD_CARD, inactive)
    }
  }
}

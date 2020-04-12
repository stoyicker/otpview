package otpview.demo

import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.util.StateSet
import android.view.View
import otpview.OtpView

internal class BoxBackgroundController(
    private val otpView: OtpView, activeView: View, inactiveView: View)
  : InactiveActiveDrawableController(activeView = activeView, inactiveView = inactiveView) {
  override fun onNewValues(active: Drawable, inactive: Drawable) {
    CustomApplication.DrawableHolder.apply {
      boxBackgroundActive = active
      boxBackgroundInactive = inactive
    }
    otpView.boxBackground = StateListDrawable().apply {
      addState(intArrayOf(android.R.attr.state_focused), active)
      addState(StateSet.WILD_CARD, inactive)
    }
  }
}

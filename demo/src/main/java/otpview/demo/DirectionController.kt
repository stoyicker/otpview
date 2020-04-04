package otpview.demo

import android.view.View
import android.widget.Checkable
import android.widget.LinearLayout

internal class DirectionController(private val linearLayout: LinearLayout, checkable: Checkable)
  : ToggleController(checkable) {
  fun onLtr() = setActive(true)

  fun onRtl() = setActive(false)

  override fun onActive() {
    linearLayout.layoutDirection = View.LAYOUT_DIRECTION_LTR
  }

  override fun onInactive() {
    linearLayout.layoutDirection = View.LAYOUT_DIRECTION_RTL
  }
}

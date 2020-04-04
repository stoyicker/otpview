package otpview.demo

import android.widget.Checkable
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL

internal class OrientationController(private val linearLayout: LinearLayout, checkable: Checkable)
  : ToggleController(checkable) {
  fun onVertical() = setActive(false)

  fun onHorizontal() = setActive(true)

  override fun onActive() {
    linearLayout.orientation = HORIZONTAL
  }

  override fun onInactive() {
    linearLayout.orientation = VERTICAL
  }
}

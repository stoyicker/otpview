package otpview.demo

import android.widget.Checkable

internal abstract class ToggleController(private val checkable: Checkable) {
  fun setActive(newActive: Boolean) {
    checkable.isChecked = newActive
    if (newActive) {
      onActive()
    } else {
      onInactive()
    }
  }

  abstract fun onActive()

  abstract fun onInactive()
}

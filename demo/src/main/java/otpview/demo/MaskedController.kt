package otpview.demo

import android.widget.Checkable
import otpview.OtpView

internal class MaskedController(private val otpView: OtpView, checkable: Checkable)
  : ToggleController(checkable) {
  fun onMask() = setActive(false)

  fun onUnmask() = setActive(true)

  override fun onActive() {
    otpView.isMasked = false
  }

  override fun onInactive() {
    otpView.isMasked = true
  }
}

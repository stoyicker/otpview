package otpview.demo

import otpview.OtpView

internal class BoxMarginTopController(private val view: OtpView) : BoxMarginController() {
  override fun getCurrentValue() = view.boxMarginTop

  override fun onNewValue(newValue: Int) {
    view.boxMarginTop = newValue
  }
}

package otpview.demo

import otpview.OtpView

internal class BoxMarginStartController(private val view: OtpView) : BoxMarginController() {
  override fun getCurrentValue() = view.boxMarginStart

  override fun onNewValue(newValue: Int) {
    view.boxMarginStart = newValue
  }
}

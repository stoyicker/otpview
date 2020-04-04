package otpview.demo

import otpview.OtpView

internal class BoxMarginEndController(private val view: OtpView) : BoxMarginController() {
  override fun getCurrentValue() = view.boxMarginEnd

  override fun onNewValue(newValue: Int) {
    view.boxMarginEnd = newValue
  }
}

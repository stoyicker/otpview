package otpview.demo

import otpview.OtpView

internal class BoxPaddingStartController(private val view: OtpView) : BoxPaddingController() {
  override fun getCurrentValue() = view.boxPaddingStart

  override fun onNewValue(newValue: Int) {
    view.boxPaddingStart = newValue
  }
}

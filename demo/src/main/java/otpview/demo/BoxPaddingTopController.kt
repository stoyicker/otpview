package otpview.demo

import otpview.OtpView

internal class BoxPaddingTopController(private val view: OtpView) : BoxPaddingController() {
  override fun getCurrentValue() = view.boxPaddingTop

  override fun onNewValue(newValue: Int) {
    view.boxPaddingTop = newValue
  }
}

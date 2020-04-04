package otpview.demo

import otpview.OtpView

internal class BoxPaddingEndController(private val view: OtpView) : BoxPaddingController() {
  override fun getCurrentValue() = view.boxPaddingEnd

  override fun onNewValue(newValue: Int) {
    view.boxPaddingEnd = newValue
  }
}

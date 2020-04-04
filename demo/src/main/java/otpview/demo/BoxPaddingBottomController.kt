package otpview.demo

import otpview.OtpView

internal class BoxPaddingBottomController(private val view: OtpView) : BoxPaddingController() {
  override fun getCurrentValue() = view.boxPaddingBottom

  override fun onNewValue(newValue: Int) {
    view.boxPaddingBottom = newValue
  }
}

package otpview.demo

import otpview.OtpView

internal class BoxMarginBottomController(private val view: OtpView) : BoxMarginController() {
  override fun getCurrentValue() = view.boxMarginBottom

  override fun onNewValue(newValue: Int) {
    view.boxMarginBottom = newValue
  }
}

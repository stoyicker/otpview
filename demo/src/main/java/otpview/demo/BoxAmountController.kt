package otpview.demo

import otpview.OtpView
import kotlin.math.abs

internal class BoxAmountController(private val view: OtpView) : MinusPlusController {
  override fun onMinus() = update(-1)

  override fun onPlus() = update(1)

  private fun update(sign: Int) {
    view.boxAmount = (view.boxAmount + sign / abs(sign)).coerceAtLeast(1)
  }
}

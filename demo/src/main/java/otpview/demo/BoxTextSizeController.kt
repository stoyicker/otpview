package otpview.demo

import otpview.OtpView
import kotlin.math.abs

internal class BoxTextSizeController(private val view: OtpView) : MinusPlusController {
  override fun onMinus() = update(-1)

  override fun onPlus() = update(1)

  private fun update(sign: Int) {
    view.boxTextSize += sign / abs(sign) * STEP_PX
  }
}

private const val STEP_PX = 1

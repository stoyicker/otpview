package otpview.demo

import kotlin.math.abs

internal abstract class BoxPaddingController : MinusPlusController {
  override fun onMinus() = update(-1)

  override fun onPlus() = update(1)

  private fun update(sign: Int) = onNewValue(getCurrentValue() + sign / abs(sign) * STEP_PX)

  abstract fun getCurrentValue(): Int

  abstract fun onNewValue(newValue: Int)
}

private const val STEP_PX = 2

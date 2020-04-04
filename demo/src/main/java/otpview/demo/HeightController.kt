package otpview.demo

import android.view.View
import kotlin.math.abs

internal class WidthController(private val view: View) : MinusPlusController {
  override fun onMinus() = update(-1)

  override fun onPlus() = update(1)

  private fun update(sign: Int) {
    view.layoutParams = view.layoutParams.apply {
      width = view.width + sign / abs(sign) * STEP_PX
    }
  }
}

private const val STEP_PX = 5

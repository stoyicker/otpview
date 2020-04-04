package otpview.demo

import android.graphics.Color
import java.util.Random

internal object ColorRandomizer : Randomizer<Int> {
  private val random = Random()

  override fun next() = random.run { Color.argb(255, nextInt(256), nextInt(256), nextInt(256)) }
}

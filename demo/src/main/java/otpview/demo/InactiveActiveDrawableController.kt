package otpview.demo

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.ViewCompat
import kotlin.properties.Delegates

internal abstract class InactiveActiveDrawableController(
    protected val activeView: View,
    protected val inactiveView: View) :
    DynamicActiveInactiveUiController<Drawable>(), RandomizableActiveInactive<Int> {
  @Suppress("RemoveExplicitTypeArguments") // Kotlin bug
  private var colorActive: Int? by Delegates.observable<Int?>(null) { _, _, new ->
    ViewCompat.setBackgroundTintList(activeView, ColorStateList.valueOf(new!!))
  }

  @Suppress("RemoveExplicitTypeArguments") // Kotlin bug
  private var colorInactive: Int? by Delegates.observable<Int?>(null) { _, _, new ->
    ViewCompat.setBackgroundTintList(inactiveView, ColorStateList.valueOf(new!!))
  }
  override val randomizer = ColorRandomizer

  override fun updateUiImpl(recoveredActive: Drawable, recoveredInactive: Drawable) {
    colorActive = (recoveredActive as ColorDrawable).color
    colorInactive = (recoveredInactive as ColorDrawable).color
  }

  override fun onRandomActive() {
    colorActive = randomizer.next()
    onNewValues(ColorDrawable(colorActive!!), ColorDrawable(colorInactive!!))
  }

  override fun onRandomInactive() {
    colorInactive = randomizer.next()
    onNewValues(ColorDrawable(colorActive!!), ColorDrawable(colorInactive!!))
  }
}

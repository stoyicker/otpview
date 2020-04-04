package otpview.demo

import android.content.res.ColorStateList
import android.util.StateSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat
import otpview.OtpView

internal class BoxTextColorController(
    private val otpView: OtpView,
    private val activeView: View,
    private val inactiveView: View) :
    DynamicActiveInactiveUiController<Int>(), RandomizableActiveInactive<Int> {
  override val randomizer = ColorRandomizer

  override fun updateUiImpl(@ColorInt recoveredActive: Int, @ColorInt recoveredInactive: Int) {
    ViewCompat.setBackgroundTintList(activeView, ColorStateList.valueOf(recoveredActive))
    ViewCompat.setBackgroundTintList(inactiveView, ColorStateList.valueOf(recoveredInactive))
  }

  override fun onRandomActive() =
      (randomizer.next() to
          ViewCompat.getBackgroundTintList(inactiveView).defaultColor).let { (active, inactive) ->
        onNewValues(active, inactive)
        updateUiImpl(active, inactive)
      }

  override fun onRandomInactive() =
      (ViewCompat.getBackgroundTintList(activeView).defaultColor to randomizer.next())
          .let { (active, inactive) ->
            onNewValues(active, inactive)
            updateUiImpl(active, inactive)
          }

  override fun onNewValues(@ColorInt active: Int, @ColorInt inactive: Int) {
    otpView.setBoxTextColorStateList(ColorStateList(
        arrayOf(intArrayOf(android.R.attr.state_focused), StateSet.WILD_CARD),
        intArrayOf(active, inactive)))
  }
}

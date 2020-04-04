package otpview.demo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import otpview.OtpInputListener
import otpview.OtpView

@Suppress("UNUSED_PARAMETER") // Required to refer to the functions directly from XML
internal class MainActivity : Activity() {
  private lateinit var otpView: OtpView
  private lateinit var widthController: MinusPlusController
  private lateinit var heightController: MinusPlusController
  private lateinit var boxTextSizeController: MinusPlusController
  private lateinit var boxAmountController: MinusPlusController
  private lateinit var boxMarginStartController: MinusPlusController
  private lateinit var boxMarginTopController: MinusPlusController
  private lateinit var boxMarginEndController: MinusPlusController
  private lateinit var boxMarginBottomController: MinusPlusController
  private lateinit var boxPaddingStartController: MinusPlusController
  private lateinit var boxPaddingTopController: MinusPlusController
  private lateinit var boxPaddingEndController: MinusPlusController
  private lateinit var boxPaddingBottomController: MinusPlusController
  private lateinit var boxTextColorController: BoxTextColorController
  private lateinit var boxBackgroundController: BoxBackgroundController
  private lateinit var boxMaskController: BoxMaskController
  private lateinit var maskedController: MaskedController
  private lateinit var orientationController: OrientationController
  private lateinit var directionController: DirectionController
  private var listener: OtpInputListener? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    otpView = findViewById(R.id.otp)
    widthController = WidthController(otpView)
    heightController = HeightController(otpView)
    boxTextSizeController = BoxTextSizeController(otpView)
    boxAmountController = BoxAmountController(otpView)
    boxMarginStartController = BoxMarginStartController(otpView)
    boxMarginTopController = BoxMarginTopController(otpView)
    boxMarginEndController = BoxMarginEndController(otpView)
    boxMarginBottomController = BoxMarginBottomController(otpView)
    boxPaddingStartController = BoxPaddingStartController(otpView)
    boxPaddingTopController = BoxPaddingTopController(otpView)
    boxPaddingEndController = BoxPaddingEndController(otpView)
    boxPaddingBottomController = BoxPaddingBottomController(otpView)
    boxTextColorController = BoxTextColorController(
        otpView,
        activeView = findViewById(R.id.button_box_text_color_active),
        inactiveView = findViewById(R.id.button_box_text_color_inactive)).apply {
      updateUi(
          recoveredActive = otpView.boxTextColor.getColorForState(
              intArrayOf(android.R.attr.state_focused), -1),
          recoveredInactive = otpView.boxTextColor.defaultColor)
    }
    boxBackgroundController = BoxBackgroundController(
        otpView,
        activeView = findViewById(R.id.button_box_background_active),
        inactiveView = findViewById(R.id.button_box_background_inactive)).apply {
      updateUi(
          recoveredActive = CustomApplication.DrawableHolder.boxBackgroundActive,
          recoveredInactive = CustomApplication.DrawableHolder.boxBackgroundInactive)
    }
    boxMaskController = BoxMaskController(
        otpView,
        activeView = findViewById(R.id.button_box_mask_active),
        inactiveView = findViewById(R.id.button_box_mask_inactive)).apply {
      updateUi(
          recoveredActive = CustomApplication.DrawableHolder.boxMaskActive,
          recoveredInactive = CustomApplication.DrawableHolder.boxMaskInactive)
    }
    maskedController = MaskedController(
        otpView,
        findViewById<CompoundButton>(R.id.button_masked)).apply {
      if (otpView.isMasked) {
        onMask()
      } else {
        onUnmask()
      }
    }
    orientationController = OrientationController(
        otpView,
        findViewById<CompoundButton>(R.id.button_orientation)).apply {
      if (otpView.orientation == VERTICAL) {
        onVertical()
      } else {
        onHorizontal()
      }
    }
    directionController = DirectionController(
        otpView,
        findViewById<CompoundButton>(R.id.button_direction)).apply {
      if (otpView.layoutDirection == View.LAYOUT_DIRECTION_LTR) {
        onLtr()
      } else {
        onRtl()
      }
    }
    listener = OtpInputListener { _, _ ->
      findViewById<TextView>(R.id.edittext_text).text = otpView.text
    }
    otpView.registerOtpInputListener(listener!!)
  }

  override fun onDestroy() {
    listener?.let { otpView.unregisterOtpInputListener(it) }
    super.onDestroy()
  }

  fun onMinusWidth(ignored: View) = widthController.onMinus()

  fun onPlusWidth(ignored: View) = widthController.onPlus()

  fun onMinusHeight(ignored: View) = heightController.onMinus()

  fun onPlusHeight(ignored: View) = heightController.onPlus()

  fun onMinusBoxTextSize(ignored: View) = boxTextSizeController.onMinus()

  fun onPlusBoxTextSize(ignored: View) = boxTextSizeController.onPlus()

  fun onMinusBoxAmount(ignored: View) = boxAmountController.onMinus()

  fun onPlusBoxAmount(ignored: View) = boxAmountController.onPlus()

  fun onMinusBoxMarginStart(ignored: View) = boxMarginStartController.onMinus()

  fun onPlusBoxMarginStart(ignored: View) = boxMarginStartController.onPlus()

  fun onMinusBoxMarginTop(ignored: View) = boxMarginTopController.onMinus()

  fun onPlusBoxMarginTop(ignored: View) = boxMarginTopController.onPlus()

  fun onMinusBoxMarginEnd(ignored: View) = boxMarginEndController.onMinus()

  fun onPlusBoxMarginEnd(ignored: View) = boxMarginEndController.onPlus()

  fun onMinusBoxMarginBottom(ignored: View) = boxMarginBottomController.onMinus()

  fun onPlusBoxMarginBottom(ignored: View) = boxMarginBottomController.onPlus()

  fun onMinusBoxPaddingStart(ignored: View) = boxPaddingStartController.onMinus()

  fun onPlusBoxPaddingStart(ignored: View) = boxPaddingStartController.onPlus()

  fun onMinusBoxPaddingTop(ignored: View) = boxPaddingTopController.onMinus()

  fun onPlusBoxPaddingTop(ignored: View) = boxPaddingTopController.onPlus()

  fun onMinusBoxPaddingEnd(ignored: View) = boxPaddingEndController.onMinus()

  fun onPlusBoxPaddingEnd(ignored: View) = boxPaddingEndController.onPlus()

  fun onMinusBoxPaddingBottom(ignored: View) = boxPaddingBottomController.onMinus()

  fun onPlusBoxPaddingBottom(ignored: View) = boxPaddingBottomController.onPlus()

  fun onLinkedin(ignored: View) =
      startActivity(Intent(Intent.ACTION_VIEW,
          Uri.parse("http://www.linkedin.com/profile/view?id=jorgediazbenitosoriano")))

  fun onGithub(ignored: View) =
      startActivity(Intent(Intent.ACTION_VIEW,
          Uri.parse("http://www.github.com/stoyicker/otpview")))

  fun onRandomBoxTextColorInactive(ignored: View) = boxTextColorController.onRandomInactive()

  fun onRandomBoxTextColorActive(ignored: View) = boxTextColorController.onRandomActive()

  fun onRandomBoxBackgroundInactive(ignored: View) = boxBackgroundController.onRandomInactive()

  fun onRandomBoxBackgroundActive(ignored: View) = boxBackgroundController.onRandomActive()

  fun onRandomBoxMaskInactive(ignored: View) = boxMaskController.onRandomInactive()

  fun onRandomBoxMaskActive(ignored: View) = boxMaskController.onRandomActive()

  fun onToggleMask(ignored: View) = if (otpView.isMasked) {
    maskedController.onUnmask()
  } else {
    maskedController.onMask()
  }

  fun onToggleOrientation(ignored: View) = if (otpView.orientation == VERTICAL) {
    orientationController.onHorizontal()
  } else {
    orientationController.onVertical()
  }

  fun onToggleDirection(ignored: View) = if (otpView.layoutDirection == View.LAYOUT_DIRECTION_LTR) {
    directionController.onRtl()
  } else {
    directionController.onLtr()
  }

  fun onSetText(ignored: View) {
    otpView.setText(findViewById<TextView>(R.id.edittext_text).text)
  }
}

package otpview.demo

import android.app.Activity
import android.os.Bundle
import otpview.OtpView

internal class MainActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val otpView = findViewById<OtpView>(R.id.otp)
  }
}

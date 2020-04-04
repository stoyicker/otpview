package otpview.demo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View

internal class MainActivity : Activity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  fun onLinkedin(ignored: View) =
      startActivity(Intent(Intent.ACTION_VIEW,
          Uri.parse("http://www.linkedin.com/profile/view?id=jorgediazbenitosoriano")))

  fun onGithub(ignored: View) =
      startActivity(Intent(Intent.ACTION_VIEW,
          Uri.parse("http://www.github.com/stoyicker/otpview")))
}

package otpview.demo

internal interface RandomizableActiveInactive<T> {
  val randomizer: Randomizer<T>

  fun onRandomActive()
  fun onRandomInactive()
}

package otpview.demo

internal abstract class DynamicActiveInactiveUiController<T> {
  fun updateUi(recoveredActive: T, recoveredInactive: T) {
    updateUiImpl(recoveredActive, recoveredInactive)
    onNewValues(recoveredActive, recoveredInactive)
  }

  abstract fun updateUiImpl(recoveredActive: T, recoveredInactive: T)

  abstract fun onNewValues(active: T, inactive: T)
}

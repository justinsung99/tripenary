package id.ac.ui.cs.mobileprogramming.justin.tripenary.base

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.justin.tripenary.injection.component.DaggerViewModelInjector
import id.ac.ui.cs.mobileprogramming.justin.tripenary.injection.component.ViewModelInjector
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips.PlannedTripsViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .build()

    init {
        inject()
    }


    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
//            is PlannedTripsViewModel -> injector.inject(this)
        }
    }
}
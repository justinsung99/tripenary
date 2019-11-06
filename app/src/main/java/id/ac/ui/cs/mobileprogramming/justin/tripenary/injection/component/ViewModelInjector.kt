package id.ac.ui.cs.mobileprogramming.justin.tripenary.injection.component

import dagger.Component
import id.ac.ui.cs.mobileprogramming.justin.tripenary.injection.module.NetworkModule
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips.PlannedTripsViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PlannedTripsViewModel.
     * @param postListViewModel PlannedTripsViewModel in which to inject the dependencies
     */
    fun inject(plannedTripsViewModel: PlannedTripsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

//        fun networkModule(networkModule: NetworkModule): Builder
    }
}
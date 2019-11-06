package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.AppDatabase
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.PlannedTrips
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.PlannedTripsRepository
import kotlinx.coroutines.launch

class PlannedTripsViewModel(application: Application) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: PlannedTripsRepository
    // LiveData gives us updated words when they change.
    val allPlannedTrips: LiveData<List<PlannedTrips>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val plannedTripsDao = AppDatabase.getDatabase(application, viewModelScope).plannedTripsDao()
        repository = PlannedTripsRepository(plannedTripsDao)
        allPlannedTrips = repository.allPlannedTrips
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(plannedTrips: PlannedTrips) = viewModelScope.launch {
        repository.insert(plannedTrips)
    }

}
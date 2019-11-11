package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.PlannedTrips
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.PlannedTripsRepository
import androidx.annotation.NonNull


class PlannedTripsViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private val repository: PlannedTripsRepository
    private val allPlannedTrips: LiveData<List<PlannedTrips>>

    init {
        repository = PlannedTripsRepository(application)
        allPlannedTrips = repository.getAllPlannedTrips()
    }

    fun insert(plannedTrip: PlannedTrips) {
        repository.insert(plannedTrip)
    }

    fun update(plannedTrip: PlannedTrips) {
        repository.update(plannedTrip)
    }

    fun delete(plannedTrip: PlannedTrips) {
        repository.delete(plannedTrip)
    }

    fun deleteAllNotes() {
        repository.deleteAll()
    }

    fun getAllPlannedTrips(): LiveData<List<PlannedTrips>> {
        return allPlannedTrips
    }
}

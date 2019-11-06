package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.lifecycle.LiveData

class PlannedTripsRepository (private val plannedTripsDao: PlannedTripsDao) {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPlannedTrips: LiveData<List<PlannedTrips>> = plannedTripsDao.all()

    suspend fun insert(plannedTrips: PlannedTrips) {
        return plannedTripsDao.insert(plannedTrips)
    }

}
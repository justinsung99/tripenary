package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.lifecycle.LiveData
import android.os.AsyncTask
import android.app.Application




class PlannedTripsRepository(application: Application) {
    private val plannedTripsDao: PlannedTripsDao
    private val allPlannedTrips: LiveData<List<PlannedTrips>>

    init {
        val database = PlannedTripsDatabase.getInstance(application)
        plannedTripsDao = database.plannedTripsDao()
        allPlannedTrips = plannedTripsDao.all()
    }

    fun insert(plannedTrips: PlannedTrips) {
        InsertPlannedTripsAsyncTask(plannedTripsDao).execute(plannedTrips)
    }

    fun update(plannedTrips: PlannedTrips) {
        UpdatePlannedTripsAsyncTask(plannedTripsDao).execute(plannedTrips)
    }

    fun delete(plannedTrips: PlannedTrips) {
        DeletePlannedTripsAsyncTask(plannedTripsDao).execute(plannedTrips)
    }

    fun deleteAll() {
        DeleteAllPlannedTripsAsyncTask(plannedTripsDao).execute()
    }

    fun getAllPlannedTrips(): LiveData<List<PlannedTrips>> {
        return allPlannedTrips
    }

    private class InsertPlannedTripsAsyncTask constructor(private val plannedTripsDao: PlannedTripsDao) :
        AsyncTask<PlannedTrips, Void, Void>() {

        override fun doInBackground(vararg plannedTrips: PlannedTrips): Void? {
            plannedTripsDao.insert(plannedTrips[0])
            return null
        }
    }

    private class UpdatePlannedTripsAsyncTask constructor(private val plannedTripsDao: PlannedTripsDao) :
        AsyncTask<PlannedTrips, Void, Void>() {

        override fun doInBackground(vararg plannedTrips: PlannedTrips): Void? {
            plannedTripsDao.update(plannedTrips[0])
            return null
        }
    }

    private class DeletePlannedTripsAsyncTask constructor(private val plannedTripsDao: PlannedTripsDao) :
        AsyncTask<PlannedTrips, Void, Void>() {

        override fun doInBackground(vararg plannedTrips: PlannedTrips): Void? {
            plannedTripsDao.delete(plannedTrips[0])
            return null
        }
    }

    private class DeleteAllPlannedTripsAsyncTask constructor(private val plannedTripsDao: PlannedTripsDao) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            plannedTripsDao.deleteAll()
            return null
        }
    }
}
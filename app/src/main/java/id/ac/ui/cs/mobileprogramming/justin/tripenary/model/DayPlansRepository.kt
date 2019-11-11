package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class DayPlansRepository(application: Application) {
    private val dayPlansDao: DayPlansDao

    init {
        val database = TripenaryDatabase.getInstance(application)
        dayPlansDao = database.dayPlansDao()

    }

    fun insert(dayPlans: DayPlans) {
        InsertDayPlanAsyncTask(dayPlansDao).execute(dayPlans)
    }

    fun update(dayPlans: DayPlans) {
        UpdateDayPlansAsyncTask(dayPlansDao).execute(dayPlans)
    }

    fun delete(dayPlans: DayPlans) {
        DeleteDayPlansAsyncTask(dayPlansDao).execute(dayPlans)
    }

    fun deleteAll() {
        DeleteAllDayPlansAsyncTask(dayPlansDao).execute()
    }

    fun getAllDayPlans(trip_id: Int): LiveData<List<DayPlans>> {
        val allDayPlans = dayPlansDao.fetchAll(trip_id)
        println(allDayPlans)
        return allDayPlans
    }

    private class InsertDayPlanAsyncTask constructor(private val dayPlansDao: DayPlansDao) :
        AsyncTask<DayPlans, Void, Void>() {

        override fun doInBackground(vararg dayPlans: DayPlans): Void? {
            dayPlansDao.insert(dayPlans[0])
            return null
        }
    }

    private class UpdateDayPlansAsyncTask constructor(private val dayPlansDao: DayPlansDao) :
        AsyncTask<DayPlans, Void, Void>() {

        override fun doInBackground(vararg dayPlans: DayPlans): Void? {
            dayPlansDao.update(dayPlans[0])
            return null
        }
    }

    private class DeleteDayPlansAsyncTask constructor(private val dayPlansDao: DayPlansDao) :
        AsyncTask<DayPlans, Void, Void>() {

        override fun doInBackground(vararg dayPlans: DayPlans): Void? {
            dayPlansDao.delete(dayPlans[0])
            return null
        }
    }

    private class DeleteAllDayPlansAsyncTask constructor(private val dayPlansDao: DayPlansDao) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            dayPlansDao.deleteAll()
            return null
        }
    }
}
package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class DayEventsRepository(application: Application) {
    private val dayEventsDao: DayEventsDao

    init {
        val database = TripenaryDatabase.getInstance(application)
        dayEventsDao = database.dayEventsDao()

    }

    fun insert(dayEvents: DayEvents) {
        InsertDayEventAsyncTask(dayEventsDao).execute(dayEvents)
    }

    fun update(dayEvents: DayEvents) {
        UpdateDayEventAsyncTask(dayEventsDao).execute(dayEvents)
    }

    fun delete(dayEvents: DayEvents) {
        DeleteDayEventAsyncTask(dayEventsDao).execute(dayEvents)
    }

    fun deleteAll() {
        DeleteAllDayEventAsyncTask(dayEventsDao).execute()
    }

    fun getAllDayEvents(day_plan_id: Int): LiveData<List<DayEvents>> {
        val allDayEvents = dayEventsDao.fetchAll(day_plan_id)
        return allDayEvents
    }

    private class InsertDayEventAsyncTask constructor(private val dayEventsDao: DayEventsDao) :
        AsyncTask<DayEvents, Void, Void>() {

        override fun doInBackground(vararg dayEvents: DayEvents): Void? {
            dayEventsDao.insert(dayEvents[0])
            return null
        }
    }

    private class UpdateDayEventAsyncTask constructor(private val dayEventsDao: DayEventsDao) :
        AsyncTask<DayEvents, Void, Void>() {

        override fun doInBackground(vararg dayEvents: DayEvents): Void? {
            dayEventsDao.update(dayEvents[0])
            return null
        }
    }

    private class DeleteDayEventAsyncTask constructor(private val dayEventsDao: DayEventsDao) :
        AsyncTask<DayEvents, Void, Void>() {

        override fun doInBackground(vararg dayEvents: DayEvents): Void? {
            dayEventsDao.delete(dayEvents[0])
            return null
        }
    }

    private class DeleteAllDayEventAsyncTask constructor(private val dayEventsDao: DayEventsDao) :
        AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            dayEventsDao.deleteAll()
            return null
        }
    }
}
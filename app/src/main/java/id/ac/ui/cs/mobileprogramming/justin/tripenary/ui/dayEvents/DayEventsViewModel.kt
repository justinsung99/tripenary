package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.dayEvents

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.DayEvents
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.DayEventsRepository

class DayEventsViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private val repository: DayEventsRepository

    init {
        repository = DayEventsRepository(application)
    }

    fun insert(dayEvents: DayEvents) {
        repository.insert(dayEvents)
    }

    fun update(dayEvents: DayEvents) {
        repository.update(dayEvents)
    }

    fun delete(dayEvents: DayEvents) {
        repository.delete(dayEvents)
    }

    fun deleteAllEvents() {
        repository.deleteAll()
    }

    fun getAllDayEvents(day_plan_id : Int): LiveData<List<DayEvents>> {
        val allDayEvents: LiveData<List<DayEvents>> = repository.getAllDayEvents(day_plan_id)
        return allDayEvents
    }
}
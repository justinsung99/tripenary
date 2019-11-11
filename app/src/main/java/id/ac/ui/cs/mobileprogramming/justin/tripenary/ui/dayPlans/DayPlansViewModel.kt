package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.dayPlans

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.DayPlans
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.DayPlansRepository

class DayPlansViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private val repository: DayPlansRepository

    init {
        repository = DayPlansRepository(application)
    }

    fun insert(dayPlans: DayPlans) {
        repository.insert(dayPlans)
    }

    fun update(dayPlans: DayPlans) {
        repository.update(dayPlans)
    }

    fun delete(dayPlans: DayPlans) {
        repository.delete(dayPlans)
    }

    fun deleteAllNotes() {
        repository.deleteAll()
    }

    fun getAllDayPlans(trip_id : Int): LiveData<List<DayPlans>> {
        val allDayPlans: LiveData<List<DayPlans>> = repository.getAllDayPlans(trip_id)
        return allDayPlans
    }
}
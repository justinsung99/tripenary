package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DayEventsDao {
    @Query("SELECT  *  FROM day_events WHERE day_plan_id IN (:day_plan_id)")
    fun fetchAll(day_plan_id : Int): LiveData<List<DayEvents>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dayEvents: DayEvents)

    @Delete
    fun delete(dayEvents: DayEvents)

    @Update
    fun update(dayEvents: DayEvents)

    @Query("DELETE FROM day_events")
    fun deleteAll()
}
package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DayPlansDao {
    @Query("SELECT  *  FROM  day_plans WHERE trip_id IN (:trip_id)")
    fun fetchAll(trip_id : Int): LiveData<List<DayPlans>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(dayPlans: DayPlans)

    @Delete
    fun delete(dayPlans: DayPlans)

    @Update
    fun update(dayPlans: DayPlans)

    @Query("DELETE FROM day_plans")
    fun deleteAll()
}
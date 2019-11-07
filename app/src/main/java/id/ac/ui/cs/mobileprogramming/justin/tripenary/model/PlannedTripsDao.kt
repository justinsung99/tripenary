package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Delete
import androidx.room.Update

@Dao
interface PlannedTripsDao {
    @Query("SELECT  *  FROM  planned_trips")
    fun all(): LiveData<List<PlannedTrips>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(plannedTrips: PlannedTrips)

    @Delete
    fun delete(plannedTrips: PlannedTrips)

    @Update
    fun update(plannedTrips: PlannedTrips)

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(plannedTrip: PlannedTrips)

    @Query("DELETE FROM planned_trips")
    fun deleteAll()
}
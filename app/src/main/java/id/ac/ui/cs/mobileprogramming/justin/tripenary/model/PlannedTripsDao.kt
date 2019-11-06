package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlannedTripsDao {
    @Query("SELECT  *  FROM  planned_trips")
    fun all(): LiveData<List<PlannedTrips>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(plannedTrip: PlannedTrips)

    @Query("DELETE FROM planned_trips")
    suspend fun deleteAll()
}
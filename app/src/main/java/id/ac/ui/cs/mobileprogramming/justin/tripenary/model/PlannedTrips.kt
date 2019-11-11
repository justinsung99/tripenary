package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planned_trips")
data class PlannedTrips(val place: String, val startDate: String, val endDate: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}

package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @param startDate: temporary casted to String
 * @param endDate: temporary casted to String
 * */
@Entity(tableName = "planned_trips")
data class PlannedTrips(@PrimaryKey val id: Int, val place: String, val startDate: String, val endDate: String)
package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "day_plans",
    foreignKeys = [
        ForeignKey(
        entity = PlannedTrips::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("trip_id"),
        onDelete = CASCADE
        )
    ],
    indices = [(Index(value = ["id"]))]
)
data class DayPlans(val title: String, val date: String, val trip_id: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

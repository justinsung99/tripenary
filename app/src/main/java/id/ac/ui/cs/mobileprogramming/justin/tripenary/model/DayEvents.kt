package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "day_events",
    foreignKeys = [
        ForeignKey(
            entity = DayPlans::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("day_plan_id"),
            onDelete = CASCADE
        )
    ]
)
data class DayEvents(
    val title: String,
    val time: String,
    val day_plan_id: Int,
    val location: String,
    val attachments: String?,
    val description: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

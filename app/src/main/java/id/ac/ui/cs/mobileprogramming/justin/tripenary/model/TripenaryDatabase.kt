package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PlannedTrips::class, DayPlans::class, DayEvents::class],
    version = 5,
    exportSchema = false
)
abstract class TripenaryDatabase : RoomDatabase() {

    abstract fun plannedTripsDao() : PlannedTripsDao

    abstract fun dayPlansDao() : DayPlansDao

    abstract fun dayEventsDao() : DayEventsDao

    companion object {

        private var INSTANCE: TripenaryDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TripenaryDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TripenaryDatabase::class.java,
                    "planned_trips_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}
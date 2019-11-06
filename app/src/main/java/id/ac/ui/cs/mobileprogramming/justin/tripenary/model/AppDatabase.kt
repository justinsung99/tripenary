package id.ac.ui.cs.mobileprogramming.justin.tripenary.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(PlannedTrips::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plannedTripsDao() : PlannedTripsDao

    private class PlannedTripsCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var plannedTripsDao = database.plannedTripsDao()

                    // Delete all content here.
                    plannedTripsDao.deleteAll()

                    // add sample trips

                    var trips = PlannedTrips(0, "Jakarta", "29 Aug 2019", "31 Aug 2019")
                    plannedTripsDao.insert(trips)
                    trips = PlannedTrips(1, "Bali", "29 Sep 2019", "02 Oct 2019")
                    plannedTripsDao.insert(trips)
                    trips = PlannedTrips(2, "Bandung", "29 Aug 2019", "31 Aug 2019")
                    plannedTripsDao.insert(trips)

                }

            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "planned_trips_database"
                )
                    .addCallback(PlannedTripsCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

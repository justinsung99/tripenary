package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.SingleFragmentActivity
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.CHANNEL_ID

class PlannedTripsActivity: SingleFragmentActivity() {

    override fun createFragment() = PlannedTripsListFragment.newInstance(null)

    override fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
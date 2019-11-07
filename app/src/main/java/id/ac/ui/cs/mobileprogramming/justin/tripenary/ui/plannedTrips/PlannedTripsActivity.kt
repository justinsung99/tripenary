package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips

import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.SingleFragmentActivity

class PlannedTripsActivity: SingleFragmentActivity() {
    override fun createFragment() = PlannedTripsListFragment.newInstance(null)
}
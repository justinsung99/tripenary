package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.PlannedTrips

class PlannedTripsViewHolder(inflater: LayoutInflater, parent:ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.planned_trips_item, parent, false)) {
    private var mPlaceView: TextView? = null
    private var mDateView: TextView? = null

    init {
        mPlaceView = itemView.findViewById(R.id.planned_trips_place)
        mDateView = itemView.findViewById(R.id.planned_trips_date)
    }

    fun bind(plannedTrips: PlannedTrips) {
        mPlaceView?.text = plannedTrips.place
        mDateView?.text = plannedTrips.startDate + " - " + plannedTrips.endDate
    }
}
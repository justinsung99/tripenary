package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.PlannedTrips

class PlannedTripsListAdapter(private val list:List<PlannedTrips>)
    : RecyclerView.Adapter<PlannedTripsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannedTripsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlannedTripsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PlannedTripsViewHolder, position: Int) {
        val plannedTrips: PlannedTrips = list[position]
        println(plannedTrips)
        holder.bind(plannedTrips)
    }

    override fun getItemCount(): Int = list.size
}
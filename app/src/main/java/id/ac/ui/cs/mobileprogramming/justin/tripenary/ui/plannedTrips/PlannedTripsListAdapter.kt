package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.PlannedTrips

class PlannedTripsListAdapter internal constructor(context: Context?, val itemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<PlannedTripsViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<PlannedTrips>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannedTripsViewHolder {
        return PlannedTripsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: PlannedTripsViewHolder, position: Int) {
        val plannedTrips: PlannedTrips = list[position]
        holder.bind(plannedTrips, itemClickListener)
    }

    internal fun setPlannedTrips(plannedTrips: List<PlannedTrips>) {
        this.list = plannedTrips
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}

interface OnItemClickListener {
    fun onItemClicked(plannedTrips: PlannedTrips)
}
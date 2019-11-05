package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.PlannedTrips
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import kotlinx.android.synthetic.main.fragment_planned_trips.*
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.addPlannedTrips.AddPlannedTripsFragment

class PlannedTripsListFragment: Fragment() {
    private val mPlannedTrips = listOf(
        PlannedTrips("Jakarta", "29 Aug 2019", "31 Aug 2019"),
        PlannedTrips("Bali", "29 Sep 2019", "02 Oct 2019"),
        PlannedTrips("Bandung", "29 Aug 2019", "31 Aug 2019"),
        PlannedTrips("Japan", "02 Jan 2019", "31 Feb 2019"),
        PlannedTrips("Sydney", "15 Mar 2019", "16 Mar 2019"),
        PlannedTrips("Sydey", "16 Mar 2019", "16 Mar 2019"),
        PlannedTrips("Sydeya", "16 Mar 2019", "17 Mar 2019"),
        PlannedTrips("Sydeyb", "17 Mar 2019", "18 Mar 2019"),
        PlannedTrips("New York", "20 Dec 2019", "01 Jan 2020"),
        PlannedTrips("New York", "20 Dec 2019", "01 Jan 2020")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_planned_trips, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initiate RecyclerView Node

        planned_trips_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior

            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = PlannedTripsListAdapter(mPlannedTrips)
        }

        // set onclick listener on "+ add planned trips" button
        add_planned_trips_button.setOnClickListener {
            val fm = activity?.supportFragmentManager

            val fragment = AddPlannedTripsFragment.newInstance()

            if (fm != null) {
                fm.beginTransaction()
                    .replace(R.id.main_fragment, fragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(activity, "[debug] fm not loaded", Toast.LENGTH_SHORT).show()
            }

        }
    }

    companion object {

        fun newInstance(): PlannedTripsListFragment = PlannedTripsListFragment()
    }

}
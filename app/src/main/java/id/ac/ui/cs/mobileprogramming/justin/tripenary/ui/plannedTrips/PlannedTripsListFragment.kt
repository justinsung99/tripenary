package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.PlannedTrips
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import kotlinx.android.synthetic.main.fragment_planned_trips.*
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.addPlannedTrips.AddPlannedTripsFragment
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.EXTRA_PLANNED_END_DATE
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.EXTRA_PLANNED_PLACE
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.EXTRA_PLANNED_START_DATE
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.NEW_PLANNED_TRIPS
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.EXTRA_PLANNED_TRIP_ID
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.dayPlans.DayPlansListFragment


class PlannedTripsListFragment: Fragment(), OnItemClickListener {

    private lateinit var plannedTripsViewModel: PlannedTripsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        plannedTripsViewModel = ViewModelProviders.of(this).get(PlannedTripsViewModel::class.java)

        println(arguments?.getBundle(NEW_PLANNED_TRIPS))
        arguments?.getBundle(NEW_PLANNED_TRIPS)?.let {
            bundle ->
                println("ayam")
                val place = bundle.get(EXTRA_PLANNED_PLACE).toString()
                val startDate = bundle.get(EXTRA_PLANNED_START_DATE).toString()
                val endDate = bundle.get(EXTRA_PLANNED_END_DATE).toString()

                val newPlannedTrips = PlannedTrips(
                    place = place,
                    startDate = startDate,
                    endDate = endDate
                )
                println(newPlannedTrips)
                plannedTripsViewModel.insert(newPlannedTrips)
                Toast.makeText(activity, "new planned trip saved", Toast.LENGTH_SHORT).show()
        }
    }

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
        val mAdapter = PlannedTripsListAdapter(context, itemClickListener = this)
        planned_trips_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior

            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = mAdapter
        }

        plannedTripsViewModel = ViewModelProviders.of(this).get(PlannedTripsViewModel::class.java)
        plannedTripsViewModel.getAllPlannedTrips().observe(this, Observer<List<PlannedTrips>> {
            //update RecyclerView
                plannedTrips -> mAdapter.setPlannedTrips(plannedTrips)
        })

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

    override fun onItemClicked(plannedTrips: PlannedTrips) {

        val bundle = Bundle()
        bundle.putInt(EXTRA_PLANNED_TRIP_ID, plannedTrips.id)
        bundle.putString(EXTRA_PLANNED_PLACE, plannedTrips.place)

        val fm = activity?.supportFragmentManager

        val fragment = DayPlansListFragment.newInstance(bundle)

        if (fm != null) {
            fm.beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        fun newInstance(bundle: Bundle?) = PlannedTripsListFragment().apply {
            arguments = Bundle().apply {
                putBundle(NEW_PLANNED_TRIPS, bundle)
            }
        }
    }

}

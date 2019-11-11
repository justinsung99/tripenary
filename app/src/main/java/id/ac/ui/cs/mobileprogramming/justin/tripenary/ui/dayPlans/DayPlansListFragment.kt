package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.dayPlans

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.DayPlans
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.addDayPlan.AddDayPlanFragment
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.*
import kotlinx.android.synthetic.main.fragment_day_trips.*

class DayPlansListFragment : Fragment(), OnItemClickListener {

    private lateinit var dayPlansViewModel: DayPlansViewModel

//    private val dayPlans : List<DayPlans>? = null
    private var tripId : Int = -1
    private var tripPlace : String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)

        dayPlansViewModel = ViewModelProviders.of(this).get(DayPlansViewModel::class.java)

        arguments?.getBundle(NEW_DAY_PLAN)?.let { bundle ->
//            val dayPlansAdapter = DayPlansListAdapter(activity,this)

            if(bundle.containsKey(EXTRA_DAY_PLAN_TITLE)) {
                saveNewDayTrip(dayPlansViewModel, bundle)
            }

            if (tripId == -1) {
                tripId = bundle.get(EXTRA_PLANNED_TRIP_ID).toString().toInt()
                tripPlace = bundle.get(EXTRA_PLANNED_PLACE).toString()


            }

//            if(tripId != -1) {
//                dayPlansViewModel.getAllDayPlans(tripId).observe(this, Observer<List<DayPlans>> {
//                    //update RecyclerView
//                        dayPlans -> dayPlansAdapter.setDayPlans(dayPlans)
//                })
//            } else {
//                println("[ERROR] trip id is not initialized")
//            }

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
    ): View? = inflater.inflate(R.layout.fragment_day_trips, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dayPlansAdapter = DayPlansListAdapter(activity,this)

        label_day_plans.text = String.format("My $tripPlace Day Plans")

        day_trips_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior

            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = dayPlansAdapter
        }

        dayPlansViewModel = ViewModelProviders.of(this).get(DayPlansViewModel::class.java)
        println(tripId)
        if(tripId != -1) {
            dayPlansViewModel.getAllDayPlans(tripId).observe(this, Observer<List<DayPlans>> {
                //update RecyclerView
                    dayPlans -> dayPlansAdapter.setDayPlans(dayPlans)
                    println(dayPlans)
            })
        }

        add_day_trips_button.setOnClickListener {

            val bundle = Bundle()
            bundle.putInt(EXTRA_PLANNED_TRIP_ID, tripId)
            val fm = activity?.supportFragmentManager

            val fragment = AddDayPlanFragment.newInstance(bundle)

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

    override fun onItemClicked(dayPlans: DayPlans) {

        val bundle = Bundle()
//        bundle.putString(EXTRA_PLANNED_PLACE, plannedTrips.place)
//
//        val fm = activity?.supportFragmentManager
//
//        val fragment = DayPlansListFragment.newInstance(bundle)
//
//        if (fm != null) {
//            fm.beginTransaction()
//                .replace(R.id.main_fragment, fragment)
//                .addToBackStack(null)
//                .commit()
//        }
    }

    private fun saveNewDayTrip(viewModel: DayPlansViewModel ,bundle: Bundle) {
        val title = bundle.get(EXTRA_DAY_PLAN_TITLE).toString()
        val date = bundle.get(EXTRA_DAY_PLAN_DATE).toString()
        val tripID = bundle.get(EXTRA_PLANNED_TRIP_ID).toString().toInt()

        val newDayPlans = DayPlans(title = title, date = date, trip_id = tripID)

        viewModel.insert(newDayPlans)
    }

    companion object {

        fun newInstance(bundle: Bundle?) = DayPlansListFragment().apply {
            arguments = Bundle().apply {
                println(bundle)
                putBundle(NEW_DAY_PLAN, bundle)
            }
        }
    }

}
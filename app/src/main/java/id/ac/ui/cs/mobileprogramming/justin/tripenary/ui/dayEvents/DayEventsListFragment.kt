package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.dayEvents

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
import id.ac.ui.cs.mobileprogramming.justin.tripenary.model.DayEvents
import kotlinx.android.synthetic.main.fragment_event_plans.*
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.addDayEvent.AddDayEventFragment
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.*

class DayEventsListFragment : Fragment(), OnItemClickListener {

    private var dayPlanID : Int = -1
    private var dayPlanTitle : String = ""

    private lateinit var dayEventsViewModel: DayEventsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        dayEventsViewModel = ViewModelProviders.of(this).get(DayEventsViewModel::class.java)
        arguments?.getBundle(EVENTS_BUNDLE)?.let { bundle ->

            if (dayPlanID == -1) {
                dayPlanID = bundle.get(EXTRA_DAY_PLAN_ID).toString().toInt()
                dayPlanTitle = bundle.get(EXTRA_DAY_PLAN_TITLE).toString()
            }

            if (bundle.containsKey(EXTRA_EVENT_PLAN_NAME)) {
                saveNewDayEvent(dayEventsViewModel, bundle)
            }
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
    ): View? = inflater.inflate(R.layout.fragment_event_plans, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        label_event_plans.text = dayPlanTitle

//         Initiate RecyclerView Node
        val mAdapter = DayEventsListAdapter(context, itemClickListener = this)
        event_plans_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior

            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = mAdapter
        }
//
        dayEventsViewModel = ViewModelProviders.of(this).get(DayEventsViewModel::class.java)
        if(dayPlanID!= -1) {
            dayEventsViewModel.getAllDayEvents(dayPlanID).observe(this, Observer<List<DayEvents>> {
                //update RecyclerView
                    dayEvents -> mAdapter.setDayEvents(dayEvents)
            })
        }//
        // set onclick listener on "+ add event" button
        add_event_plans_button.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(EXTRA_DAY_PLAN_ID, dayPlanID)
            val fm = activity?.supportFragmentManager

            val fragment = AddDayEventFragment.newInstance(bundle)

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

    override fun onItemClicked(dayPlans: DayEvents) {

    }

    private fun saveNewDayEvent(viewModel: DayEventsViewModel, bundle: Bundle) {
        val name = bundle.get(EXTRA_EVENT_PLAN_NAME).toString()
        val time = bundle.get(EXTRA_EVENT_PLAN_TIME).toString()
        val dayPlanID = bundle.get(EXTRA_DAY_PLAN_ID).toString().toInt()
        val location = bundle.get(EXTRA_EVENT_PLAN_LOCATION).toString()
        val description = bundle.get(EXTRA_EVENT_PLAN_DESCRIPTION).toString()
        val attachment = bundle.get(EXTRA_EVENT_PLAN_ATTACHMENT).toString()

        val newDayEvent = DayEvents(
            title = name,
            time= time,
            day_plan_id= dayPlanID,
            location = location,
            description = description,
            attachments = attachment
        )

        viewModel.insert(newDayEvent)
    }

    companion object {

        fun newInstance(bundle: Bundle?) = DayEventsListFragment().apply {
            arguments = Bundle().apply {
                println(bundle)
                putBundle(EVENTS_BUNDLE, bundle)
            }
        }
    }
}
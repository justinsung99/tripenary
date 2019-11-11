package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.addDayPlan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import kotlinx.android.synthetic.main.fragment_add_day_trip.*
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.dayPlans.DayPlansListFragment
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.*

class AddDayPlanFragment : Fragment() {
    private var tripId : Int = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)

        arguments?.getBundle(NEW_DAY_PLAN)?.let { bundle ->

            tripId = bundle.get(EXTRA_PLANNED_TRIP_ID).toString().toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_add_day_trip, container, false)

        val startDDSpinner : Spinner = view.findViewById(R.id.day_trip_date_dd_input)
        val startMMSpinner : Spinner = view.findViewById(R.id.day_trip_date_mm_input)

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.dd_array,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            startDDSpinner.adapter = arrayAdapter
        }

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.mm_array,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            startMMSpinner.adapter = arrayAdapter
        }


//        startDDSpinner.onItemSelectedListener = this
//        startMMSpinner.onItemSelectedListener = this

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancel_new_day_plan_btn.setOnClickListener {
            // when cancel btn clicked, back to previous fragment
            val fm = activity?.supportFragmentManager

            fm?.popBackStack()
        }

        save_new_day_plan_btn.setOnClickListener{
            saveDayTrip()
        }
    }

    private fun saveDayTrip() {
        val title = day_plan_title_input.text.toString()

        val dateDD = day_trip_date_dd_input.selectedItem.toString()
        val dateMM = day_trip_date_mm_input.selectedItem.toString()
        val dateYYYY = day_trip_date_yyyy_input.text.toString()

        // check for empty here

        // parse number to string here
        val dateMonth = Months.mmToMonth(dateMM.toInt())
        val date = String.format("$dateDD $dateMonth $dateYYYY")

        val bundle = Bundle()
        bundle.putString(EXTRA_DAY_PLAN_TITLE, title)
        bundle.putString(EXTRA_DAY_PLAN_DATE, date)
        bundle.putInt(EXTRA_PLANNED_TRIP_ID, tripId)

        val fm = activity?.supportFragmentManager

        val fragment = DayPlansListFragment.newInstance(bundle)

        fm?.let {
            fm.beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
    companion object {
        fun newInstance(bundle: Bundle?) = AddDayPlanFragment().apply {
            arguments = Bundle().apply {
                putBundle(NEW_DAY_PLAN, bundle)
            }
        }
    }
}
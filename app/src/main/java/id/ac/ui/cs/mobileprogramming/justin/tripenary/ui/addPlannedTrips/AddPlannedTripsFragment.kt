package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.addPlannedTrips


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import kotlinx.android.synthetic.main.fragment_add_planned_trips.*
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips.PlannedTripsListFragment
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.EXTRA_PLANNED_END_DATE
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.EXTRA_PLANNED_PLACE
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.EXTRA_PLANNED_START_DATE
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.Months



class AddPlannedTripsFragment: Fragment(), AdapterView.OnItemSelectedListener {

//    private var startDD : Int = 0
//    private var startMM : Int = 0
//    private var startYYYY : Int = 0
//
//    private var endDD : Int = 0
//    private var endMM : Int = 0
//    private var endYYYY : Int = 0

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        println(parent?.getItemAtPosition(position))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_add_planned_trips, container, false)

        val startDDSpinner : Spinner = view.findViewById(R.id.start_date_dd_input)
        val startMMSpinner : Spinner = view.findViewById(R.id.start_date_mm_input)

        val endDDSpinner : Spinner = view.findViewById(R.id.end_date_dd_input)
        val endMMSpinner : Spinner = view.findViewById(R.id.end_date_mm_input)

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.dd_array,
            android.R.layout.simple_spinner_item
            ).also { arrayAdapter ->
                // Specify the layout to use when the list of choices appears
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                startDDSpinner.adapter = arrayAdapter
                endDDSpinner.adapter = arrayAdapter
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
            endMMSpinner.adapter = arrayAdapter
        }


        startDDSpinner.onItemSelectedListener = this
        startMMSpinner.onItemSelectedListener = this
        endDDSpinner.onItemSelectedListener = this
        endMMSpinner.onItemSelectedListener = this

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancel_new_planned_trips_btn.setOnClickListener {
            // when cancel btn clicked, back to previous fragment
            val fm = activity?.supportFragmentManager

            fm?.popBackStack()
        }

        save_new_planned_trips_btn.setOnClickListener{
            savePlannedTrip()
        }
    }

    private fun savePlannedTrip() {
        val place = trip_name_input.text.toString()

        val start_dd = start_date_dd_input.selectedItem.toString()
        val start_mm = start_date_mm_input.selectedItem.toString()
        val start_yyyy = start_date_yyyy_input.text.toString()

        val end_dd = end_date_dd_input.selectedItem.toString()
        val end_mm = end_date_mm_input.selectedItem.toString()
        val end_yyyy = end_date_yyyy_input.text.toString()

        // check for empty here

        // parse number to string here
        val startMonth = Months.mmToMonth(start_mm.toInt())
        val endMonths = Months.mmToMonth(end_mm.toInt())
        val startDate = String.format("$start_dd $startMonth $start_yyyy")
        val endDate = String.format("$end_dd $endMonths $end_yyyy")

        val bundle = Bundle()
        bundle.putString(EXTRA_PLANNED_PLACE, place)
        bundle.putString(EXTRA_PLANNED_START_DATE, startDate)
        bundle.putString(EXTRA_PLANNED_END_DATE, endDate)

        val fm = activity?.supportFragmentManager

        val fragment = PlannedTripsListFragment.newInstance(bundle)

        fm?.let {
            fm.beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        fun newInstance(): AddPlannedTripsFragment = AddPlannedTripsFragment()
    }
}
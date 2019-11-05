package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.addPlannedTrips


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import kotlinx.android.synthetic.main.fragment_add_planned_trips.*


class AddPlannedTripsFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_planned_trips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancel_new_planned_trips_btn.setOnClickListener {
            // when cancel btn clicked, back to previous fragment
            val fm = activity?.supportFragmentManager

            fm?.popBackStack()
        }
    }

    companion object {
        fun newInstance(): AddPlannedTripsFragment = AddPlannedTripsFragment()
    }
}
package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.addDayEvent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R
import kotlinx.android.synthetic.main.fragment_add_event_plan.*
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.dayEvents.DayEventsListFragment
import id.ac.ui.cs.mobileprogramming.justin.tripenary.utils.*
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import android.content.Intent
import android.os.Build
import java.io.File
import android.Manifest
import android.app.Activity
import android.app.PendingIntent
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.PermissionChecker.PERMISSION_DENIED
import androidx.core.content.PermissionChecker.checkSelfPermission
import id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.plannedTrips.PlannedTripsActivity
import java.util.*
import kotlin.random.Random

class AddDayEventFragment : Fragment() {
    private var dayPlanID : Int = -1
    private var eventLocation: String = ""
    private var eventDesc: String = ""
    private var attachmentPath: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)

        arguments?.getBundle(NEW_EVENT_PLAN)?.let { bundle ->

            dayPlanID = bundle.get(EXTRA_DAY_PLAN_ID).toString().toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_add_event_plan, container, false)

        val startHSpinner : Spinner = view.findViewById(R.id.event_plan_start_time_h_input)
        val endHSpinner : Spinner = view.findViewById(R.id.event_plan_end_time_h_input)

        val startMinMinSpinner : Spinner = view.findViewById(R.id.event_plan_start_time_minmin_input)
        val endMinMinSpinner : Spinner = view.findViewById(R.id.event_plan_end_time_minmin_input)

        val startAMPMSpinner : Spinner = view.findViewById(R.id.event_plan_start_time_ampm_input)
        val endAMPMSpinner : Spinner = view.findViewById(R.id.event_plan_end_time_ampm_input)

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.hour_array,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            startHSpinner.adapter = arrayAdapter
            endHSpinner.adapter = arrayAdapter
        }

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.minmin_array,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            startMinMinSpinner.adapter = arrayAdapter
            endMinMinSpinner.adapter = arrayAdapter
        }

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.am_pm_array,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            // Specify the layout to use when the list of choices appears
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            startAMPMSpinner.adapter = arrayAdapter
            endAMPMSpinner.adapter = arrayAdapter
        }

//        startDDSpinner.onItemSelectedListener = this
//        startMMSpinner.onItemSelectedListener = this

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancel_new_event_plan_btn.setOnClickListener {
            // when cancel btn clicked, back to previous fragment
            val fm = activity?.supportFragmentManager

            fm?.popBackStack()
        }

        event_plan_location_input.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

                // you can call or do what you want with your EditText here

                 eventLocation = event_plan_location_input.text.toString()

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        event_plan_description_input.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

                // you can call or do what you want with your EditText here

                eventDesc = event_plan_description_input.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        event_plan_location_input.setOnFocusChangeListener {
                v, hasFocus ->
            println(hasFocus)
            if(hasFocus) {
                if (eventLocation == "") {
                    event_plan_location_input.setText("")
                } else {
                    println(eventLocation)
                }
            }
        }

        event_plan_description_input.setOnFocusChangeListener {
                v, hasFocus ->
            println(hasFocus)
            if(hasFocus) {
                if (eventDesc == "") {
                    event_plan_description_input.setText("")
                }
            }
        }

        event_plan_attachment_input_clickable.setOnClickListener {
            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(context!!,Manifest.permission.READ_EXTERNAL_STORAGE) == PERMISSION_DENIED) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //permission already granted
                    pickImageFromGallery()
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
//            Toast.makeText(activity, "click!", Toast.LENGTH_SHORT).show()
        }

        save_new_event_plan_btn.setOnClickListener{
            saveEvent()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE ) {
            println(data)
            data?.let {
                attachmentPath = data.data.toString()
                val file = File(attachmentPath)
                println(file)

//                val imgName = attachmentPath.split("/").last()
                event_plan_attachment_text.text = attachmentPath

            }
        }
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

    private fun saveEvent() {
        val eventName = event_plan_name_input.text.toString()

        val startHTime = event_plan_start_time_h_input.selectedItem.toString()
        val endHTime = event_plan_end_time_h_input.selectedItem.toString()

        val startMinMinTime = event_plan_start_time_minmin_input.selectedItem.toString()
        val endMinMinTime = event_plan_end_time_minmin_input.selectedItem.toString()

        val startAMPMTime = event_plan_start_time_ampm_input.selectedItem.toString()
        val endAMPMTime = event_plan_end_time_ampm_input.selectedItem.toString()

        // description
        val eventDesc = event_plan_description_input.text.toString()

        // check for empty here
        // 8:00 AM - 12:00 PM
        val time = String.format("$startHTime:$startMinMinTime $startAMPMTime - $endHTime:$endMinMinTime $endAMPMTime")

        val bundle = Bundle()
        bundle.putString(EXTRA_EVENT_PLAN_NAME, eventName)
        bundle.putString(EXTRA_EVENT_PLAN_TIME, time)
        bundle.putString(EXTRA_EVENT_PLAN_DESCRIPTION, eventDesc)
        bundle.putString(EXTRA_EVENT_PLAN_ATTACHMENT, attachmentPath)
        bundle.putString(EXTRA_EVENT_PLAN_LOCATION, eventLocation)
        bundle.putInt(EXTRA_DAY_PLAN_ID, dayPlanID)

        val fm = activity?.supportFragmentManager

        val fragment = DayEventsListFragment.newInstance(bundle)

        // Create an explicit intent for an Activity in your app
        val intent = Intent(context!!, PlannedTripsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context!!, 0, intent, 0)


        val builder = NotificationCompat.Builder(context!!, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("New Event Created!")
            .setContentText(String.format("Your event $eventName is created successfully"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context!!)) {
            // notificationId is a unique int for each notification that you must define
            notify(Random.nextInt(), builder.build())
        }


        fm?.let {
            fm.beginTransaction()
                .replace(R.id.main_fragment, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
    companion object {
        private val PERMISSION_CODE = 1001

        fun newInstance(bundle: Bundle?) = AddDayEventFragment().apply {
            arguments = Bundle().apply {
                putBundle(NEW_EVENT_PLAN, bundle)
            }
        }
    }
}
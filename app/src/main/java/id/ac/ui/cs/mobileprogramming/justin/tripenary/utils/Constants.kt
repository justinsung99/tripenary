package id.ac.ui.cs.mobileprogramming.justin.tripenary.utils

/** The base URL of the API */
const val BASE_URL: String = "https://maps.googleapis.com/maps/api/place/autocomplete/json?key=AIzaSyBIFUkxAq_mg_SKqEO-FAKaR987E4tj1iQ"

/** string key constant used primarily in adding new planned trips */
const val EXTRA_PLANNED_PLACE = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_PLANNED_PLACE"
const val EXTRA_PLANNED_START_DATE = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_PLANNED_START_DATE"
const val EXTRA_PLANNED_END_DATE = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_PLANNED_END_DATE"
const val NEW_PLANNED_TRIPS = "id.ac.ui.cs.mobileprogramming.justin.tripenary.NEW_PLANNED_TRIPS"

const val NEW_DAY_PLAN = "id.ac.ui.cs.mobileprogramming.justin.tripenary.NEW_DAY_PLAN"

const val NEW_EVENT_PLAN = "id.ac.ui.cs.mobileprogramming.justin.tripenary.NEW_EVENT_PLAN"

const val EXTRA_DAY_PLAN_TITLE = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_DAY_PLAN_TITLE"
const val EXTRA_DAY_PLAN_DATE = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_DAY_PLAN_DATE"
const val EXTRA_DAY_PLAN_ID = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_DAY_PLAN_ID"

const val EXTRA_PLANNED_TRIP_ID = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_PLANNED_TRIP_ID"

const val EVENTS_BUNDLE = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EVENTS_BUNDLE"

const val EXTRA_EVENT_PLAN_NAME = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_EVENT_PLAN_NAME"
const val EXTRA_EVENT_PLAN_TIME = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_EVENT_PLAN_TIME"
const val EXTRA_EVENT_PLAN_LOCATION = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_EVENT_PLAN_LOCATION"
const val EXTRA_EVENT_PLAN_DESCRIPTION = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_EVENT_PLAN_DESCRIPTION"
const val EXTRA_EVENT_PLAN_ATTACHMENT = "id.ac.ui.cs.mobileprogramming.justin.tripenary.EXTRA_EVENT_PLAN_ATTACHMENT"

const val PICK_IMAGE = 110 // random number

const val CHANNEL_ID = "Tripenary.notification.success.create"

fun searchPlace(input: String) {
    val api = BASE_URL + String.format("&input=$input")
}
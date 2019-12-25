package id.ac.ui.cs.mobileprogramming.justin.tripenary.ui.searchTrip

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle;
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.jesualex.autocompletelocation.*
import id.ac.ui.cs.mobileprogramming.justin.tripenary.R


class SearchTripActivity : FragmentActivity(),
    OnMapReadyCallback,
    AutocompleteLocationListener,
    OnSearchListener,
    OnPlaceLoadListener
{
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_destination)

        if(!isNetworkAvailable()) {
            Toast.makeText(this, "No Internet Connection Detected! please check your internet connection", Toast.LENGTH_LONG).show()
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val bounds = RectangularBounds.newInstance(
            LatLng(-33.880490, 151.184363),
            LatLng(-33.858754, 151.229596))

        val autoCompleteLocation = findViewById<AutocompleteLocation>(R.id.autocomplete_location)
        autoCompleteLocation.setAutoCompleteTextListener(this)
        autoCompleteLocation.setOnSearchListener(this)
        autoCompleteLocation.setLocationBias(bounds)

        //Set placeListener to auto calculate Place object when a AutocompletePrediction has selected.
        autoCompleteLocation.setPlaceListener(this)

        //Set placeFields to receive only the fields what you need. By default PlaceUtil.getDefaultFields() is call.
        autoCompleteLocation.setPlaceFields(PlaceUtils.defaultFields)

    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val jakarta = LatLng(-6.175110, 106.865039)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, 16f))
    }

    override fun onTextClear() {
        mMap!!.clear()
    }

    override fun onItemSelected(selectedPlace: AutocompletePrediction?) {
        Log.i(javaClass.simpleName, "A autocomplete has selected: ")
        selectedPlace?.let { logAutocomplete(it) }
    }

    override fun onSearch(address: String, predictions: List<AutocompletePrediction>) {
        for (prediction in predictions) {
            logAutocomplete(prediction)
        }
    }

    override fun onPlaceLoad(place: Place) {
        addMapMarker(place.latLng)
    }

    private fun logAutocomplete(selectedPrediction: AutocompletePrediction) {
        Log.i(javaClass.simpleName, selectedPrediction.placeId)
        Log.i(javaClass.simpleName, selectedPrediction.getPrimaryText(null).toString())
    }

    private fun addMapMarker(latLng: LatLng?) {
        mMap!!.clear()
        mMap!!.addMarker(MarkerOptions().position(latLng!!))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
    }

}
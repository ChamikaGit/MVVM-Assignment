package com.digikraft.bikestation.ui.home

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.digikraft.bikestation.R
import com.digikraft.bikestation.databinding.FragmentDetailsBinding
import com.digikraft.bikestation.utils.Constants.TAG
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StationsDetailsFragment : Fragment(),OnMapReadyCallback {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val supportMapFragment = childFragmentManager.findFragmentById(binding.mapFragment.id) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        initGoogleMap(map)
    }

    private fun initGoogleMap(map: GoogleMap) {
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(), R.raw.style_json
                )
            )
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        val latLong = LatLng(6.901019, 79.853032)
        map.clear()
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLong, 10.0F))
        map.addMarker(
            MarkerOptions()
                .position(latLong)
                .title("Marker in Bike Station")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

}